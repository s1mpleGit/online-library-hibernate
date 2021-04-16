package library.persistence.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "users")
//@ToString(exclude = "users")

@Entity
@Table(name = "STATUSES")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String status;

    public Status(String status) {
        this.status = status;
    }

    @OneToMany(mappedBy = "status", orphanRemoval = true)
//    @Fetch(FetchMode.JOIN)
    private Set<User> users;

    public User addUser(User user) {
        user.setStatus(this);
        this.users.add(user);
        return user;
    }

    public void removeUser(User user) {
        user.setStatus(null);
        this.users.remove(user);
    }

    @Override
    public String toString() {
        return status;
    }
}