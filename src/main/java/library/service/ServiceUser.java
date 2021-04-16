package library.service;

import library.persistence.dao.DaoBook;
import library.persistence.dao.DaoRole;
import library.persistence.dao.DaoStatus;
import library.persistence.dao.DaoUser;
import library.persistence.model.Book;
import library.persistence.model.Role;
import library.persistence.model.Status;
import library.persistence.model.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class ServiceUser {

    private DaoUser userDao = new DaoUser();
    private DaoBook bookDao = new DaoBook();
    private DaoStatus statusDao = new DaoStatus();

    public User checkUserLoginPassword(String login, String password) {

        if (login != null && password != null && login.length() <= 20 && password.length() <= 20) {
            if (!login.trim().isEmpty() && !password.trim().isEmpty()) {
                List<User> users = userDao.readAll();
                return users.stream()
                        .filter(u -> u.getLogin().equalsIgnoreCase(login) && u.getPassword().equalsIgnoreCase(password))
                        .findFirst().orElse(null);
            }
            return null;
        }
        return null;
    }

    public int checkUserLoginForRegistration(String login) {

        if (login != null && login.length() <= 20) {
            if (!login.trim().isEmpty()) {
                List<User> users = userDao.readAll();
                return (int) users.stream()
                        .filter(u -> u.getLogin().equalsIgnoreCase(login))
                        .count();
            }
            return 0;
        }
        return 0;
    }

    public User getUserInfo(int id) {
        if (id > 0) {
            User user = userDao.read(id);
            if (user != null) {
                return user;
            }
            throw new RuntimeException("User with ID = " + id + " not exist");
        }
        throw new RuntimeException("User with ID = " + id + " not exist");
    }

    public User saveNewUser(User user) {

        String login = user.getLogin();
        String password = user.getPassword();
        String name = user.getName();
        String surname = user.getSurname();
        String email = user.getEmail();
        String phone = user.getPhone();

        if (name != null && surname != null && email != null && phone != null) {
            if (!name.trim().isEmpty() && !surname.trim().isEmpty() && !email.trim().isEmpty() && !phone.trim().isEmpty()) {
                if (name.length() <= 20 && surname.length() <= 20 && email.length() <= 30 && phone.length() == 12) {
                    DaoRole roleDao = new DaoRole();
                    DaoStatus statusDao = new DaoStatus();
                    Role role = roleDao.read(1);
                    Status status = statusDao.read(1);
                    User newUser = User.builder()
                            .login(login)
                            .password(password)
                            .name(name)
                            .surname(surname)
                            .email(email)
                            .phone(phone)
                            .role(role)
                            .status(status)
                            .build();
                    return userDao.create(newUser);
                }
                return null;
            }
            return null;
        }
        return null;
    }

    public User updateUserInfo(User user) {

        int id = user.getId();
        String password = user.getPassword();
        String name = user.getName();
        String surname = user.getSurname();
        String email = user.getEmail();
        String phone = user.getPhone();

        if (name != null && surname != null && email != null && phone != null && password != null && id > 0) {
            if (!name.trim().isEmpty() && !surname.trim().isEmpty() && !email.trim().isEmpty() && !phone.trim().isEmpty() && !password.trim().isEmpty()) {
                return userDao.update(user);
            }
            return null;
        }
        return null;
    }

    public boolean blockUserById(int id) {
        if (id > 0) {
            User user = userDao.read(id);
            Status status = statusDao.read(2);
            user.setStatus(status);
            userDao.update(user);
            return true;
        }
        throw new RuntimeException("Failed to block user with ID = " + id);
    }

    public User sendMessage(User user) {

        int id = user.getId();
        String message = user.getMessage();

        if (id > 0 && message != null) {
            if (!message.trim().isEmpty()) {
                return userDao.update(user);
            }
            return null;
        }
        return null;
    }

    public List<User> showAllUsers() {
        return userDao.readAll();
    }

    public boolean unblockUserById(int id) {
        if (id > 0) {
            User user = userDao.read(id);
            Status status = statusDao.read(1);
            user.setStatus(status);
            userDao.update(user);
            return true;
        }
        throw new RuntimeException("Failed to block user with ID = " + id);
    }

    public boolean deleteUser(int id) {
        if (id > 0) {
            userDao.delete(id);
            return true;
        }
        throw new RuntimeException("Failed to delete user with ID = " + id);
    }

    public User addBookForUser(int userId, int bookId) {
        User user = userDao.read(userId);
        Book book = bookDao.read(bookId);
        Set<Book> books = user.getBooks();

        long check = books.stream()
                .filter(b -> b.equals(book))
                .count();
        if (check == 0) {
            LocalDate date = LocalDate.now().plusWeeks(1);
            book.setReturn_date(date);
            user.addBook(book);
//            book.addUser(user);
            userDao.update(user);
//            bookDao.update(book);
            return user;
        } else return null;
    }

    public User removeBookFromUser(int userId, int bookId) {
        User user = userDao.read(userId);
        Book book = bookDao.read(bookId);
//        Set<Book> books = user.getBooks();
//        long check = books.stream()
//                .filter(b -> b.getId() == bookId)
//                .count();
//        if (check != 0) {
//            user.removeBook(book);
        book.removeUser(user);
//            userDao.update(user);
        bookDao.update(book);
        return user;
//        } else return null;
    }

    public Set<Book> getUserBooksByUserId(int id) {
        if (id > 0) {
            User user = userDao.read(id);
            return user.getBooks();
        }
        throw new RuntimeException("User with ID = " + id + " not exist");
    }
}
