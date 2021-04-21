package library.service;

import library.persistence.dao.*;
import library.persistence.model.Role;
import library.persistence.model.Status;
import library.persistence.model.User;
import library.persistence.model.UserBooks;

import java.time.LocalDate;
import java.util.List;

public class ServiceUser {

    private DaoUser userDao = new DaoUser();
    private DaoBook bookDao = new DaoBook();
    private DaoStatus statusDao = new DaoStatus();
    private DaoUserBooks userBooksDao = new DaoUserBooks();

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
            userDao.deleteBooksByUserId(id);
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

    public boolean addBookForUser(int userId, int bookId) {

        List<UserBooks> userBooks = userBooksDao.readAll();

        long check = userBooks.stream()
                .filter(ub -> ub.getUser().getId() == userId && ub.getBook().getId() == bookId)
                .count();
        if (check == 0) {
            LocalDate date_return = LocalDate.now().plusWeeks(1);
            userDao.saveBookForUser(userId, bookId, date_return);
            return true;
        } else return false;
    }

    public boolean removeBookFromUser(int userBookId) {

        if (userBookId > 0) {
            userDao.deleteUserBookById(userBookId);
            return true;
        } else return false;
    }

    public List<UserBooks> getUserBooks() {

        DaoUserBooks userBooks = new DaoUserBooks();
        return userBooks.readAll();
    }

}
