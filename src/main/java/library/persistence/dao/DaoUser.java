package library.persistence.dao;

import library.persistence.connector.JpaEntityManagerFactoryUtil;
import library.persistence.model.Author;
import library.persistence.model.Book;
import library.persistence.model.Role;
import library.persistence.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
//import static library.persistence.connector.HikariCP.getConnection;

public class DaoUser implements DaoInterface<User> {

    private final EntityManagerFactory entityManagerFactory = JpaEntityManagerFactoryUtil.getEntityManagerFactory();

    @Override
    public User create(User user) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process save method for User - " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return user;
    }

    @Override
    public User read(int id) {
        EntityManager entityManager = null;
        User user = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            user = entityManager.createQuery("from User u left join fetch u.role left join fetch u.status left join fetch u.books where u.id = :id",
                    User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find by id method for " + User.class.getSimpleName() + " - " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return user;
    }

    @Override
    public User update(User user) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            user = entityManager.merge(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process update method for User - " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return user;
    }

    @Override
    public void delete(int id) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.createQuery("delete from User where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process delete method for Book - " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<User> readAll() {
        EntityManager entityManager = null;
        List<User> users = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            users = entityManager.createQuery("from User u left join fetch u.role left join fetch u.status left join fetch u.books", User.class)
//                    .setParameter("id", id)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find by id method for " + User.class.getSimpleName() + " - " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return users;
    }

//
//    private PreparedStatement ps = null;
//    private ResultSet rs = null;
//
//    @Override
//    public User create(User user) throws SQLException {
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("INSERT INTO USERS (login, password, name, surname, email, phone, role, status) VALUES (?,?,?,?,?,?,?,?)", RETURN_GENERATED_KEYS);
//            ps.setString(1, user.getLogin());
//            ps.setString(2, user.getPassword());
//            ps.setString(3, user.getName());
//            ps.setString(4, user.getSurname());
//            ps.setString(5, user.getEmail());
//            ps.setString(6, user.getPhone());
//            ps.setString(7, String.valueOf(user.getRole()));
//            ps.setString(8, String.valueOf(user.getStatus()));
//            ps.executeUpdate();
//            rs = ps.getGeneratedKeys();
//            if (rs.next()) {
//                return User.builder()
//                        .id(rs.getInt(1))
//                        .login(user.getLogin())
//                        .password(user.getPassword())
//                        .name(user.getName())
//                        .surname(user.getSurname())
//                        .email(user.getEmail())
//                        .phone(user.getPhone())
//                        .role(user.getRole())
//                        .status(user.getStatus())
//                        .date_penalty(user.getDate_penalty())
//                        .build();
//            }
//            throw new RuntimeException("Failed to create user " + user.getName());
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to create user " + user.getName());
//        } finally {
//            rs.close();
//            ps.close();
//        }
//    }
//
//    @Override
//    public User read(int id) throws SQLException {
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("SELECT login, password, name, surname, email, phone, role, status, date_penalty, message FROM USERS WHERE id=?");
//            ps.setInt(1, id);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                try {
//                    Date date = rs.getDate("date_penalty");
//                    LocalDate localDate;
//                    if (date == null) {
//                        localDate = null;
//                    } else {
//                        localDate = date.toLocalDate();
//                    }
//                    return User.builder()
//                            .id(id)
//                            .login(rs.getString("login"))
//                            .password(rs.getString("password"))
//                            .name(rs.getString("name"))
//                            .surname(rs.getString("surname"))
//                            .email(rs.getString("email"))
//                            .phone(rs.getString("phone"))
////                            .role(ConstantRole.valueOf(rs.getString("role")))
////                            .status(ConstantStatus.valueOf((rs.getString("status"))))
//                            .date_penalty(localDate)
//                            .message(rs.getString("message"))
//                            .build();
//                } catch (NullPointerException npe) {
//                        npe.printStackTrace();
//                }
//            }
//            throw new RuntimeException("Failed to find user by ID = " + id);
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to find user by ID = " + id);
//        } finally {
//            rs.close();
//            ps.close();
//        }
//    }
//
//    @Override
//    public User update(User user) throws SQLException {
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("UPDATE USERS SET name=?,surname=?,email=?,phone=?,password=? WHERE id=?");
//            ps.setString(1, user.getName());
//            ps.setString(2, user.getSurname());
//            ps.setString(3, user.getEmail());
//            ps.setString(4, user.getPhone());
//            ps.setString(5, user.getPassword());
//            ps.setInt(6, user.getId());
//            ps.executeUpdate();
//            if (ps.executeUpdate() != 0) {
//                return User.builder()
//                        .id(user.getId())
//                        .password(user.getPassword())
//                        .name(user.getName())
//                        .surname(user.getSurname())
//                        .email(user.getEmail())
//                        .phone(user.getPhone())
//                        .build();
//            }
//            throw new RuntimeException("Failed to update user " + user.getName());
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to update user " + user.getName());
//        } finally {
//            ps.close();
//        }
//    }
//
//    @Override
//    public boolean delete(int id) throws SQLException {
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("DELETE FROM USERS WHERE id=?");
//            ps.setInt(1, id);
//            ps.executeUpdate();
//            return ps.executeUpdate() != 0;
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to delete user by ID = " + id);
//        } finally {
//            ps.close();
//        }
//    }
//
//    @Override
//    public List<User> readAll() throws SQLException {
//
//        List<User> users = new ArrayList<>();
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("SELECT id, login, password, name, surname, email, phone, role, status, date_penalty, message FROM USERS");
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                LocalDate localDate = null;
//                try {
//                    Date date = rs.getDate("date_penalty");
//                    if (date != null) {
//                        localDate = date.toLocalDate();
//                    }
//                } catch (NullPointerException ignored) {}
//                User user = User.builder()
//                        .id(rs.getInt("id"))
//                        .login(rs.getString("login"))
//                        .password(rs.getString("password"))
//                        .name(rs.getString("name"))
//                        .surname(rs.getString("surname"))
//                        .email(rs.getString("email"))
//                        .phone(rs.getString("phone"))
////                        .role(ConstantRole.valueOf(rs.getString("role")))
////                        .status(ConstantStatus.valueOf((rs.getString("status"))))
//                        .date_penalty(localDate)
//                        .message(rs.getString("message"))
//                        .build();
//                users.add(user);
//            }
//            return users;
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to readAll users");
//        } finally {
//            rs.close();
//            ps.close();
//        }
//    }
//
//    public User checkUserForLogin(String login, String password) throws SQLException {
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("SELECT id, status, role, date_penalty FROM USERS WHERE login LIKE ? AND password LIKE ?");
//            ps.setString(1, login);
//            ps.setString(2, password);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                int id = rs.getInt("id");
//                ConstantStatus status = ConstantStatus.valueOf((rs.getString("status")));
//                ConstantRole role = ConstantRole.valueOf(rs.getString("role"));
//                try {
//                    Date date = rs.getDate("date_penalty");
//                    LocalDate localDate;
//                    if (date == null) {
//                        localDate = null;
//                    } else {
//                        localDate = date.toLocalDate();
//                    }
//                    return User.builder()
//                            .id(id)
////                            .role(role)
////                            .status(status)
//                            .date_penalty(localDate)
//                            .build();
//                } catch (NullPointerException ignored) {
//
//                }
//            }
//            return null;
//        } catch (SQLException e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to check user");
//        } finally {
//            rs.close();
//            ps.close();
//        }
//    }
//
//    public int checkUserForRegistration(String login) throws SQLException {
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("SELECT id FROM USERS WHERE login LIKE ?");
//            ps.setString(1, login);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                return rs.getInt("id");
//            }
//            return 0;
//        } catch (SQLException e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to check user");
//        } finally {
//            rs.close();
//            ps.close();
//        }
//    }
//
//    public boolean blockUserById(int id) {
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("UPDATE USERS SET status=? WHERE id=?");
//            ps.setString(1, String.valueOf(ConstantStatus.BLOCK));
//            ps.setInt(2, id);
//            ps.executeUpdate();
//            boolean check = false;
//            if (ps.executeUpdate() != 0) {
//                check = true;
//            }
//            return check;
//        } catch (Exception e) {
//            System.out.println("Failed to connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to block user with ID = " + id);
//        } finally {
//            try {
//                ps.close();
//            } catch (SQLException throwable) {
//                throwable.printStackTrace();
//            }
//        }
//    }
//
//    public User writeMessage(User user) {
//        EntityManager entityManager = null;
//        try {
//            entityManager = entityManagerFactory.createEntityManager();
//            entityManager.getTransaction().begin();
//            entityManager.createNativeQuery("")
//                    .setParameter("id", user.getId())
//                    .executeUpdate();
//            entityManager.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println("Error process delete method for User - " + e.getMessage());
//        } finally {
//            if (entityManager != null && entityManager.isOpen()) {
//                entityManager.close();
//            }
//        }
//    }
//
//    public boolean unblockUserById(int id) {
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("UPDATE USERS SET status=? WHERE id=?");
//            ps.setString(1, String.valueOf(ConstantStatus.ACTIVE));
//            ps.setInt(2, id);
//            ps.executeUpdate();
//            boolean check = false;
//            if (ps.executeUpdate() != 0) {
//                check = true;
//            }
//            return check;
//        } catch (Exception e) {
//            System.out.println("Failed to connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to block user with ID = " + id);
//        } finally {
//            try {
//                ps.close();
//            } catch (SQLException throwable) {
//                throwable.printStackTrace();
//            }
//        }
//    }
}

