//package library.persistence.dao;
//
//import library.persistence.model.Card;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//import static java.sql.Statement.RETURN_GENERATED_KEYS;
//import static library.persistence.connector.HikariCP.getConnection;
//
//public class DaoCard implements DaoInterface<Card> {
//
//    private PreparedStatement ps = null;
//    private ResultSet rs = null;
//
//    @Override
//    public Card create(Card card) throws SQLException {
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("INSERT INTO CARDS (user_id, book_id, return_date) VALUES (?,?,?)", RETURN_GENERATED_KEYS);
//            ps.setInt(1, card.getUser_id());
//            ps.setInt(2, card.getBook_id());
//            ps.setDate(3, Date.valueOf(card.getReturn_date()));
//            ps.executeUpdate();
//            rs = ps.getGeneratedKeys();
//            if (rs.next()) {
//                return Card.builder()
//                        .id(rs.getInt(1))
//                        .user_id(card.getUser_id())
//                        .book_id(card.getBook_id())
//                        .return_date(card.getReturn_date())
//                        .build();
//            }
//            throw new RuntimeException("Failed to create card");
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to create card ");
//        } finally {
//            rs.close();
//            ps.close();
//        }
//    }
//
//    @Override
//    public Card read(int id) throws SQLException {
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("SELECT (user_id, book_id, return_date) FROM CARDS WHERE id=?");
//            ps.setInt(1, id);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                return Card.builder()
//                        .id(id)
//                        .user_id(rs.getInt("user_id"))
//                        .book_id(rs.getInt("book_id"))
//                        .return_date(rs.getDate("return_date").toLocalDate())
//                        .build();
//            }
//            throw new Exception("Failed to find card by ID = " + id);
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to find card by ID = " + id);
//        } finally {
//            rs.close();
//            ps.close();
//        }
//    }
//
//    @Override
//    public Card update(Card card) throws SQLException {
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("UPDATE CARDS SET user_id=?,book_id=?,return_date=? WHERE id=?");
//            ps.setInt(1, card.getUser_id());
//            ps.setInt(2, card.getBook_id());
//            ps.setDate(3, Date.valueOf(card.getReturn_date()));
//            ps.setInt(4, card.getId());
//            ps.executeUpdate();
//            if (ps.executeUpdate() != 0) {
//                return card;
//            }
//            throw new RuntimeException("Failed to update card");
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to update card");
//        } finally {
//            ps.close();
//        }
//    }
//
//    @Override
//    public boolean delete(int id) throws SQLException {
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("DELETE FROM CARDS WHERE id=?");
//            ps.setInt(1, id);
//            int check = ps.executeUpdate();
//            if (check != 0) {
//                return true;
//            } else return false;
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to delete card by ID = " + id);
//        } finally {
//            ps.close();
//        }
//    }
//
//    @Override
//    public List<Card> readAll() throws SQLException {
//
//        List<Card> cards = new ArrayList<>();
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement(
//                    "SELECT c.ID as id, c.USER_ID as user_id, b.TITLE as title, a.NAME as author, b.DESCRIPTION as description, c.RETURN_DATE as return_date, b.IMAGEURI as imageUri " +
//                            "FROM CARDS c " +
//                            "LEFT JOIN BOOKS b ON c.BOOK_ID = b.ID " +
//                            "LEFT JOIN AUTHORS a ON b.AUTHOR_ID = a.ID ");
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                Card card = Card.builder()
//                        .id(rs.getInt("id"))
//                        .user_id(rs.getInt("user_id"))
//                        .title(rs.getString("title"))
//                        .author(rs.getString("author"))
//                        .description(rs.getString("description"))
//                        .return_date(rs.getDate("return_date").toLocalDate())
//                        .imageUri(rs.getString("imageUri"))
//                        .build();
//                cards.add(card);
//            }
//            return cards;
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to get cards");
//        } finally {
//            rs.close();
//            ps.close();
//        }
//    }
//
//    public int check(Card card) throws SQLException {
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("SELECT id FROM CARDS WHERE user_id=? AND book_id=?");
//            ps.setInt(1, card.getUser_id());
//            ps.setInt(2, card.getBook_id());
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                return rs.getInt("id");
//                } else return 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Card already exist");
//        } finally {
//            rs.close();
//            ps.close();
//        }
//    }
//
//    public boolean deleteByBookId(int book_id) throws SQLException {
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("DELETE FROM CARDS WHERE book_id=?");
//            ps.setInt(1, book_id);
//            int check = ps.executeUpdate();
//            if (check != 0) {
//                return true;
//            } else return false;
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to delete card by ID = " + book_id);
//        } finally {
//            ps.close();
//        }
//    }
//
//    public boolean deleteByUserId(int user_id) throws SQLException {
//
//        try (Connection conn = getConnection()) {
//            ps = conn.prepareStatement("DELETE FROM CARDS WHERE user_id=?");
//            ps.setInt(1, user_id);
//            int check = ps.executeUpdate();
//            if (check != 0) {
//                return true;
//            } else return false;
//        } catch (Exception e) {
//            System.out.println("Failed connection to DB" + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to delete card by ID = " + user_id);
//        } finally {
//            ps.close();
//        }
//    }
//}
