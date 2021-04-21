package library.persistence.dao;

import library.persistence.connector.JpaEntityManagerFactoryUtil;
import library.persistence.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class DaoBook implements DaoInterface<Book> {

    private final EntityManagerFactory entityManagerFactory = JpaEntityManagerFactoryUtil.getEntityManagerFactory();

    @Override
    public Book create(Book book) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(book);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process save method for Book - " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return book;
    }

    @Override
    public Book read(int id) {
        EntityManager entityManager = null;
        Book book = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            book = entityManager.createQuery("from Book b left join fetch b.author where b.id = :id"
                    , Book.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find by id method for " + Book.class.getSimpleName() + " - " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return book;
    }

    @Override
    public Book update(Book book) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            book = entityManager.merge(book);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process update method for Book - " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return book;
    }

    @Override
    public void delete(int id) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.createQuery("delete from UserBooks ub where ub.book.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            entityManager.createQuery("delete from Book where id = :id")
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
    public List<Book> readAll() {
        EntityManager entityManager = null;
        List<Book> books = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            books = entityManager.createQuery("from Book b left join fetch b.author"
                    , Book.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find all method for " + Book.class.getSimpleName() + " - " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return books;
    }

}
