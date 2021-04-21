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
            user = entityManager.createQuery("from User u left join fetch u.role left join fetch u.status where u.id = :id",
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
            users = entityManager.createQuery("from User u left join fetch u.role left join fetch u.status", User.class)
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

    public boolean saveBookForUser(int userId, int bookId, LocalDate return_date) {

        EntityManager entityManager = null;
        boolean check = false;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            int checkQuery = entityManager.createNativeQuery("INSERT INTO USER_BOOKS (user_id, book_id, return_date) VALUES (?,?,?)")
                    .setParameter(1, userId)
                    .setParameter(2, bookId)
                    .setParameter(3, Date.valueOf(return_date))
                    .executeUpdate();
            entityManager.getTransaction().commit();
            if (checkQuery != 0) {
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process add book for user " + userId + " and book " + bookId + " - " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return check;
    }

    public void deleteUserBookById(int userBookId) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.createQuery("delete from UserBooks ub where ub.id = :id")
                    .setParameter("id", userBookId)
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

    public void deleteBooksByUserId(int userId) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.createQuery("delete from UserBooks ub where ub.user.id = :id")
                    .setParameter("id", userId)
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
}

