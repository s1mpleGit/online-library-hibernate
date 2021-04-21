package library.service;

import library.persistence.dao.DaoAuthor;
import library.persistence.model.Author;

import java.util.List;

public class ServiceAuthor {

    private DaoAuthor authorDao = new DaoAuthor();

    public List<Author> getAllAuthors() {
        return authorDao.readAll();
    }

    public Author createAuthor2(Author author) {

        String name = author.getName();
        if (name != null && !name.trim().isEmpty() && name.length() <= 20) {
            List<Author> authors = authorDao.readAll();
            long result = authors.stream()
                    .filter(au -> au.getName().equalsIgnoreCase(name))
                    .count();
            if (result == 0) {
                return authorDao.create(author);
            }
        }
        return null;
    }

    public Author getAuthorInfo(int id) {
        if (id > 0) {
            Author author = authorDao.read(id);
            if (author != null) {
                return author;
            }
            throw new RuntimeException("User with ID = " + id + " not exist");
        }
        throw new RuntimeException("User with ID = " + id + " not exist");
    }
}
