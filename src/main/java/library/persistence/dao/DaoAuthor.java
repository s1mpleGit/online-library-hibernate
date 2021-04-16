package library.persistence.dao;

import library.persistence.connector.JpaEntityManagerFactoryUtil;
import library.persistence.model.Author;
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
        List<Author> users = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            users = entityManager.createQuery("from Author a left join fetch a.books", Author.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find by id method for " + Author.class.getSimpleName() + " - " + e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return users;
    }

//    private PreparedStatement ps = null;
//    private ResultSet rs = null;
//
//    @Override
//    public Author create(Author author) throws SQLException {
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("INSERT INTO AUTHORS (name) VALUES (?)", RETURN_GENERATED_KEYS);
//            ps.setString(1, author.getName());
//            ps.executeUpdate();
//            rs = ps.getGeneratedKeys();
//            if (rs.next()) {
//                return Author.builder()
//                        .id(rs.getInt(1))
//                        .name(author.getName())
//                        .build();
//            }
//            throw new RuntimeException("Failed to create author " + author.getName());
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to create author " + author.getName());
//        } finally {
//            rs.close();
//            ps.close();
//        }
//    }
//
//    @Override
//    public Author read(int id) throws SQLException {
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("SELECT (name) FROM AUTHORS WHERE id=?");
//            ps.setInt(1, id);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                return Author.builder()
//                        .id(id)
//                        .name(rs.getString("name"))
//                        .build();
//            }
//            throw new Exception("Failed to find author by ID = " + id);
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to find author by ID = " + id);
//        } finally {
//            rs.close();
//            ps.close();
//        }
//    }
//
//    @Override
//    public Author update(Author author) throws SQLException {
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("UPDATE AUTHORS SET name=? WHERE id=?");
//            ps.setString(1, author.getName());
//            ps.setInt(2, author.getId());
//            ps.executeUpdate();
//            if (ps.executeUpdate() != 0) {
//                return author;
//            }
//            throw new RuntimeException("Failed to update author " + author.getName());
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to update author " + author.getName());
//        } finally {
//            ps.close();
//        }
//    }
//
//    @Override
//    public boolean delete(int id) throws SQLException {
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("DELETE FROM AUTHORS WHERE id=?");
//            ps.setInt(1, id);
//            ps.executeUpdate();
//            if (ps.executeUpdate() != 0) {
//                return true;
//            }
//            throw new RuntimeException("Failed to delete author by ID = " + id);
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to delete author by ID = " + id);
//        } finally {
//            ps.close();
//        }
//    }
//
//    @Override
//    public List<Author> readAll() throws SQLException {
//
//        List<Author> authors = new ArrayList<>();
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("SELECT id, name FROM AUTHORS");
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                Author author = Author.builder()
//                        .id(rs.getInt("id"))
//                        .name(rs.getString("name"))
//                        .build();
//                authors.add(author);
//            }
//            return authors;
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to readAll authors");
//        } finally {
//            rs.close();
//            ps.close();
//        }
//    }
//
//    public boolean check(Author author) throws SQLException {
//
//        boolean check = true;
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("SELECT (name) FROM AUTHORS");
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                if (rs.getString("name").equalsIgnoreCase(author.getName())) {
//                    check = false;
//                    break;
//                }
//            }
//            return check;
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Author already exist = " + author.getName());
//        } finally {
//            rs.close();
//            ps.close();
//        }
//    }

}
