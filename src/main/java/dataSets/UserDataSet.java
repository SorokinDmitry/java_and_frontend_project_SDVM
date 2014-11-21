package dataSets;

import javax.persistence.*;

/**
 * Created by serg on 21.11.14.
 */
@Entity
@Table(name = "users")
public class UserDataSet {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "score")
    private long score;

    public UserDataSet(long id, String login, String password, String email, long score) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.score = score;
    }

    public UserDataSet(String login, String password, String email) {
        this.id = -1;
        this.login = login;
        this.password = password;
        this.email = email;
        this.score = 0;
    }
}
