package library.persistence.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"user", "book"})
@ToString(exclude = {"user", "book"})

@Entity
@Table(name = "USER_BOOKS")
public class UserBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDate return_date;

    public UserBooks(LocalDate return_date) {
        this.return_date = return_date;
    }

}
