package library.service;

import library.persistence.dao.DaoBook;
import library.persistence.model.Author;
import library.persistence.model.Book;
import library.persistence.model.User;

import java.sql.SQLException;
import java.util.List;

public class ServiceBook {

    private DaoBook bookDao = new DaoBook();

    public List<Book> getAllBooks() {
        return bookDao.readAll();
    }

    public boolean deleteBook(int id) {
        if (id > 0) {
                bookDao.delete(id);
                return true;
            }
        throw new RuntimeException("Failed to delete book with ID = " + id);
    }

    public Book createBook(Book book) {

        String title = book.getTitle();
        Author author = book.getAuthor();
        String description = book.getDescription();
        String imageUri = book.getImageUri();

        if (title != null && author != null && description != null && imageUri != null) {
            if (!title.trim().isEmpty() && !description.trim().isEmpty() && !imageUri.trim().isEmpty()) {
                if (title.length() <=20) {
                    List<Book> books = bookDao.readAll();
                    long check = books.stream()
                            .filter(b -> b.getTitle().equalsIgnoreCase(title) && b.getAuthor().equals(author))
                            .count();
                    if (check == 0) {
                        return bookDao.create(book);
                    } else {
                        return null;
                    }
                } return null;
            } return null;
        } return null;

    }

    public Book updateBook(Book book) {

        int bookId = book.getId();
        String title = book.getTitle();
        Author author = book.getAuthor();
        String description = book.getDescription();
        String imageUri = book.getImageUri();

        if (title != null && author != null && bookId > 0 && description != null && imageUri != null) {
            if (!title.trim().isEmpty() && !description.trim().isEmpty() && !imageUri.trim().isEmpty()) {
                if (title.length() <= 20) {
                    return bookDao.update(book);
                } return null;
            } return null;
        } return null;
    }

    public Book getBookInfo(int id) {
        if (id > 0) {
            Book book = bookDao.read(id);
            if (book != null) {
                return book;
            } throw new RuntimeException("Book with ID = " + id + " not exist");
        } throw new RuntimeException("Book with ID = " + id + " not exist");
    }
}
