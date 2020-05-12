package wp.project.finki.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * Represents a user
 */

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @NotEmpty(message = "Username can not be null or empty")
    @Length(min = 4, max = 32, message = "Username can not be less than 4 or more than 32 characters")
    @Column(name = "username", unique = true, nullable = false, length = 32)
    private String username;
    @NotEmpty(message = "Email can not be null or empty")
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Setter
    @NotEmpty(message = "Password can not be null or empty")
    @Length(min = 8, message = "Password can not be less than 8 characters")
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Constructor
     *
     * @param user - object contains user's data
     */
    public User(User user) {
        this(user.id, user.username, user.email, user.password);
    }

    /**
     * Constructor
     *
     * @param id       user's id
     * @param username user's username
     * @param email    user's email
     * @param password user's password
     */
    public User(long id, String username, String email, String password) {
        this(username, email, password);
        this.id = id;
    }

    /**
     * Constructor
     *
     * @param username user's username
     * @param email    user's email
     * @param password user's password
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructor
     *
     * @param username user's username
     * @param email    user's email
     */
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}