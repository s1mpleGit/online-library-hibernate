package library.persistence.dao;

import library.persistence.connector.JpaEntityManagerFactoryUtil;
import library.persistence.model.Status;
import library.persistence.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class DaoStatus {

    private final EntityManagerFactory entityManagerFactory = JpaEntityManagerFactoryUtil.getEntityManagerFactory();

    public Status read(int id) {
        EntityManager entityManager = null;
        Status status = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            status = entityManager.find(Status.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find by id method for " + Status.class.getSimpleName() + " - " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return status;
    }
}
