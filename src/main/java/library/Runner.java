package library;

import library.persistence.dao.DaoAuthor;
import library.persistence.dao.DaoBook;
import library.persistence.dao.DaoUser;
import library.persistence.model.Author;
import library.persistence.model.Book;
import library.persistence.model.User;
import library.service.ServiceUser;
import org.h2.tools.Server;

import java.sql.SQLException;
import java.util.List;

public class Runner {

//    static {
//        try {
//            Server.createTcpServer().start();
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed start tcp H2 server");
//        }
//    }

//    public static void main(String[] args) {
//
////        DaoAuthor authorDao = new DaoAuthor();
////        List<Author> authors = authorDao.readAll();
////        System.out.println(authors);
////
////        DaoBook bookDao = new DaoBook();
////        List<Book> books = bookDao.readAll();
////        System.out.println(books);
////
////        DaoUser userDao = new DaoUser();
////        List<User> users = userDao.readAll();
////        System.out.println(users);
//
//        ServiceUser us = new ServiceUser();
//        User user = us.checkUserLoginPassword("admin", "admin");
//        System.out.println(user.getRole());
//    }
}
