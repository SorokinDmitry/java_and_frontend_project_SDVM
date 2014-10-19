package base;

import utils.UserProfile;

/**
 * Created by Dmitry on 019 19.10.14.
 */
public interface AccountService {
    public void addUser(UserProfile user);
    public void addSession(String id, String login);
    public int getCountOfUsers();
    public int getCountOfSessions();
    public void deleteSession(String id);
    public boolean haveSession(String id);
    public boolean haveUser(String login);
    public boolean isPasswordCorrect(String login, String password);
    public UserProfile getUserProfileByLogin(String login);
    public UserProfile getUserProfileBySessionId(String id);
}
