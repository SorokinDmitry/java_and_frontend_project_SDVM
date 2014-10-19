package utils;

/**
 * Created by Dmitry on 013 13.09.14.
 */
public class UserProfileImpl implements UserProfile {
    private String login;
    private String password;
    private String email;

    public UserProfileImpl(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
