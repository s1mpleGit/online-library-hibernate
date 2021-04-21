package library.persistence.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "books")
//@ToString(exclude = "books")

@Entity
@Table(name = "AUTHORS")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Author(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "author")
    private Set<Book> books = new HashSet<>();

    public void addBook(Book book) {
        this.books.add(book);
        book.setAuthor(this);
    }

    public void deleteBook(Book book) {
        this.books.remove(book);
        book.setAuthor(null);
    }

    @Override
    public String toString() {
        return name;
    }
}
