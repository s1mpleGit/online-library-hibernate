//package library.service;
//
//import library.persistence.dao.DaoCard;
//import library.persistence.model.Card;
//
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.List;
//
//public class ServiceCard {
//
//    private DaoCard cardDao = new DaoCard();
//
//    public int checkCard(Card card) {
//        try {
//            return cardDao.check(card);
//        } catch (SQLException throwable) {
//            throwable.printStackTrace();
//        }
//        throw new RuntimeException("Failed to check card");
//    }
//
//    public List<Card> getAllCards() {
//        try {
//            return cardDao.readAll();
//        } catch (SQLException throwable) {
//            throwable.printStackTrace();
//        }
//        throw new RuntimeException("Failed to get all cards");
//    }
//
//    public Card createCard(Card card) {
//        int userId = card.getUser_id();
//        int bookId = card.getBook_id();
//
//        if (userId > 0 && bookId > 0) {
//            LocalDate localDate = LocalDate.now();
//            Card newCard = Card.builder()
//                    .user_id(userId)
//                    .book_id(bookId)
//                    .return_date(localDate.plusWeeks(1))
//                    .build();
//            try {
//                return cardDao.create(newCard);
//            } catch (SQLException throwable) {
//                throwable.printStackTrace();
//            }
//        }
//        throw new RuntimeException("Failed to create a new card");
//    }
//
//    public boolean deleteCard(int id) {
//        if (id > 0) {
//            try {
//                return cardDao.delete(id);
//            } catch (SQLException throwable) {
//                throwable.printStackTrace();
//            }
//        }
//        throw new RuntimeException("Failed to delete card");
//    }
//
//    public boolean deleteCardByBookId(int book_id) {
//        if (book_id > 0) {
//            try {
//                return cardDao.deleteByBookId(book_id);
//            } catch (SQLException throwable) {
//                throwable.printStackTrace();
//            }
//        }
//        throw new RuntimeException("Failed to delete card");
//    }
//
//    public boolean deleteCardByUserId(int user_id) {
//        if (user_id > 0) {
//            try {
//                return cardDao.deleteByUserId(user_id);
//            } catch (SQLException throwable) {
//                throwable.printStackTrace();
//            }
//        }
//        throw new RuntimeException("Failed to delete card");
//    }
//}
