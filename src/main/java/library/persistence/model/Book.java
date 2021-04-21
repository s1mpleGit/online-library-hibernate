package library.persistence.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.type.ClobType;

import javax.persistence.*;
import java.lang.management.ClassLoadingMXBean;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"author", "userBooks"})
@ToString(exclude = {"author", "userBooks"})

@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private String imageUri;

    public Book(String title, String description, String imageUri) {
        this.title = title;
        this.description = description;
        this.imageUri = imageUri;
    }

//    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

//    @Fetch(FetchMode.JOIN)
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name = "user_books",
//    joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
//    inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
//    private Set<User> users = new HashSet<>();
    /*delete cascades*/
    @OneToMany(mappedBy = "book")
    private Set<UserBooks> userBooks;

//    public void addUser(User user) {
//        this.users.add(user);
//        user.getBooks().add(this);
//    }
//
//    public void removeUser(User user) {
//        this.users.remove(user);
//        user.getBooks().remove(this);
//    }

}
