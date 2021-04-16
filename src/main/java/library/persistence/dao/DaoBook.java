package library.persistence.dao;

import library.persistence.connector.JpaEntityManagerFactoryUtil;
import library.persistence.model.Author;
import library.persistence.model.Book;
import library.persistence.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
//import static library.persistence.connector.HikariCP.getConnection;

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
            book = entityManager.createQuery("from Book b left join fetch b.author left join fetch b.users where b.id = :id"
                    ,Book.class)
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
            books = entityManager.createQuery("from Book b left join fetch b.author left join fetch b.users"
                    ,Book.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find by id method for " + Author.class.getSimpleName() + " - " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return books;
    }

//    private PreparedStatement ps = null;
//    private ResultSet rs = null;
//
//    @Override
//    public Book create(Book book) throws SQLException {
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("INSERT INTO BOOKS (title, author_id, description, imageUri) VALUES (?,?,?,?)", RETURN_GENERATED_KEYS);
//            ps.setString(1, book.getTitle());
//            ps.setInt(2, book.getAuthor_id());
//            ps.setString(3, book.getDescription());
//            ps.setString(4, book.getImageUri());
//            ps.executeUpdate();
//            rs = ps.getGeneratedKeys();
//            if (rs.next()) {
//                return Book.builder()
//                        .id(rs.getInt(1))
//                        .title(book.getTitle())
//                        .author_id(book.getAuthor_id())
//                        .description(book.getDescription())
//                        .build();
//            }
//            throw new RuntimeException("Failed to create book " + book.getTitle());
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to create book " + book.getTitle());
//        } finally {
//            rs.close();
//            ps.close();
//        }
//    }
//
//    @Override
//    public Book read(int id) throws SQLException {
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("SELECT (title, author_id, description) FROM BOOKS WHERE id=?");
//            ps.setInt(1, id);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                return Book.builder()
//                        .id(id)
//                        .title(rs.getString("title"))
//                        .author_id(rs.getInt("author_id"))
//                        .description(rs.getString("description"))
//                        .build();
//            }
//            throw new Exception("Failed to find book by ID = " + id);
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to find book by ID = " + id);
//        } finally {
//            rs.close();
//            ps.close();
//        }
//    }
//
//    @Override
//    public Book update(Book book) throws SQLException {
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("UPDATE BOOKS SET title=?,author_id=?,description=?,imageUri=? WHERE id=?");
//            ps.setString(1, book.getTitle());
//            ps.setInt(2, book.getAuthor_id());
//            ps.setString(3, book.getDescription());
//            ps.setString(4, book.getImageUri());
//            ps.setInt(5, book.getId());
//            int update = ps.executeUpdate();
//            if (update != 0) {
//                return book;
//            }
//            throw new RuntimeException("Failed to update book " + book.getTitle());
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to update author " + book.getTitle());
//        } finally {
//            ps.close();
//        }
//    }
//
//    @Override
//    public boolean delete(int id) throws SQLException {
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("DELETE FROM BOOKS WHERE id=?");
//            ps.setInt(1, id);
//            ps.executeUpdate();
//            return ps.executeUpdate() != 0;
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to delete book by ID = " + id);
//        } finally {
//            ps.close();
//        }
//    }
//
//    @Override
//    public List<Book> readAll() throws SQLException {
//
//        List<Book> books = new ArrayList<>();
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("SELECT b.ID as id, b.TITLE as title, b.author_id as author_id,a.NAME as author, b.DESCRIPTION as description, b.IMAGEURI as image FROM BOOKS b LEFT JOIN authors a ON b.AUTHOR_ID = a.ID");
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                books.add(Book.builder()
//                        .id(rs.getInt("id"))
//                        .title(rs.getString("title"))
//                        .author_id(rs.getInt("author_id"))
////                        .author(rs.getString("author"))
//                        .description(rs.getString("description"))
//                        .imageUri(rs.getString("image"))
//                        .build());
//            }
//            return books;
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to get books");
//        } finally {
//            rs.close();
//            ps.close();
//        }
//    }
//
//    public int check(Book book) throws SQLException {
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("SELECT title FROM BOOKS WHERE author_id = ?");
//            ps.setInt(1, book.getAuthor_id());
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                if (rs.getString("title").equalsIgnoreCase(book.getTitle())) {
//                    return rs.getInt("author_id");
//                    }
//                }
//                return 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Book already exist = " + book.getTitle());
//        } finally {
//            rs.close();
//            ps.close();
//        }
//    }

}
