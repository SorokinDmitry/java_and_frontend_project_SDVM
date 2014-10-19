package base;

import utils.UserProfile;
import utils.UserProfileImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmitry on 013 13.09.14.
 */
public class AccountServiceImpl implements AccountService {
    private Map<String, UserProfile> users = new HashMap<String, UserProfile>();
    private Map<String, UserProfile> sessions = new HashMap<String, UserProfile>();

    public AccountServiceImpl() {
        addUser(new UserProfileImpl("admin","admin","admin@admin.ru"));
        addUser(new UserProfileImpl("dmitr","","sorokin.dmitr@yandex.ru"));
    }

    public void addUser(UserProfile user) {
        this.users.put(user.getLogin(), user);
    }

    public void addSession(String id, String login) {
        this.sessions.put(id, users.get(login));
    }

    public int getCountOfUsers() {
        return this.users.size();
    }

    public int getCountOfSessions() {
        return this.sessions.size();
    }

    public void deleteSession(String id) {
        this.sessions.remove(id);
    }

    public boolean haveSession(String id) {
        return  sessions.containsKey(id);
    }

    public boolean haveUser(String login) {
        return  users.containsKey(login);
    }

    public boolean isPasswordCorrect(String login, String password) {
        return password.equals(users.get(login).getPassword());
    }

    public UserProfile getUserProfileByLogin(String login) {
        return users.get(login);
    }

    public UserProfile getUserProfileBySessionId(String id) {
        return sessions.get(id);
    }
}
