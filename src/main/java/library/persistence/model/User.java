package library.persistence.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"role", "status", "userBooks"})
@ToString(exclude = {"role", "status", "userBooks"})

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String message;

    public User(String login, String password, String name, String surname, String email, String phone, String message) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.message = message;
    }

    @ManyToOne(fetch = FetchType.LAZY)
//    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
//    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

//    @ManyToMany(mappedBy = "users", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private Set<Book> books = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserBooks> userBooks;

//    public void addBook(Book book) {
//        this.books.add(book);
//        book.getUsers().add(this);
//    }
//
//    public void removeBook(Book book) {
//        this.books.remove(book);
//        book.getUsers().remove(this);
//    }
}
