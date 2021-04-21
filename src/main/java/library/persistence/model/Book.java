package library.persistence.model;

import lombok.*;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @OneToMany(mappedBy = "book")
    private Set<UserBooks> userBooks;

}
