package library.persistence.dao;

import library.persistence.connector.JpaEntityManagerFactoryUtil;
import library.persistence.model.Role;
import library.persistence.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class DaoRole {

    private final EntityManagerFactory entityManagerFactory = JpaEntityManagerFactoryUtil.getEntityManagerFactory();

    public Role read(int id) {
        EntityManager entityManager = null;
        Role role = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            role = entityManager.find(Role.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find by id method for " + Role.class.getSimpleName() + " - " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return role;
    }
}
