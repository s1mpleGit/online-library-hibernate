package library.persistence.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "users")
//@ToString(exclude = "users")

@Entity
@Table(name = "ROLES")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String role;

    public Role(String role) {
        this.role = role;
    }

    @OneToMany(mappedBy = "role", orphanRemoval = true)
    private Set<User> users;

    public User addUser(User user) {
        user.setRole(this);
        this.users.add(user);
        return user;
    }

    public void removeUser(User user) {
        user.setRole(null);
        this.users.remove(user);
    }

    @Override
    public String toString() {
        return role;
    }
}