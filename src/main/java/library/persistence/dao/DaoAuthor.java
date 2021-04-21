package library.persistence.dao;

import library.persistence.connector.JpaEntityManagerFactoryUtil;
import library.persistence.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class DaoAuthor implements DaoInterface<Author> {

    private final EntityManagerFactory entityManagerFactory = JpaEntityManagerFactoryUtil.getEntityManagerFactory();

    @Override
    public Author create(Author author) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(author);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process save method for Author - " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return author;
    }

    @Override
    public Author read(int id) {
        EntityManager entityManager = null;
        Author author = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            author = entityManager.find(Author.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find by id method for " + Author.class.getSimpleName() + " - " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return author;
    }

    @Override
    public Author update(Author author) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Author> readAll() {
        EntityManager entityManager = null;
        List<Author> authors = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            authors = entityManager.createQuery("from Author", Author.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find all method for " + Author.class.getSimpleName() + " - " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return authors;
    }

}
