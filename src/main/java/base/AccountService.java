package base;

/**
 * Created by Dmitry on 019 19.10.14.
 */
public interface AccountService {
    public boolean addUser(String login, String password, String email);
    public boolean addSession(String id, String login);
    public int getCountOfUsers();
    public int getCountOfSessions();
    public void deleteSession(String id);
    public boolean haveSession(String id);
    public boolean haveUser(String login);
    public boolean isPasswordCorrect(String login, String password);
    public String getUserEmailBySessionId(String id);
    public String getUserLoginBySessionId(String id);
}
