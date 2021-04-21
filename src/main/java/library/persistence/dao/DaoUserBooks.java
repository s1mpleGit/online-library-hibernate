package library.persistence.dao;

import library.persistence.connector.JpaEntityManagerFactoryUtil;
import library.persistence.model.UserBooks;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class DaoUserBooks {

    private final EntityManagerFactory entityManagerFactory = JpaEntityManagerFactoryUtil.getEntityManagerFactory();

    public List<UserBooks> readAll() {
        EntityManager entityManager = null;
        List<UserBooks> userBooks = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            userBooks = entityManager
                    .createQuery("from UserBooks ub left join fetch ub.user left join fetch ub.book b left join fetch b.author", UserBooks.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find all method for " + UserBooks.class.getSimpleName() + " - " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return userBooks;
    }

}