package base;

import utils.UserProfile;
import utils.UserProfile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmitry on 013 13.09.14.
 */
public class AccountServiceImpl implements AccountService {
    private Map<String, UserProfile> users = new HashMap<String, UserProfile>();
    private Map<String, String> usersId = new HashMap<String, String>();
    private Map<String, UserProfile> sessions = new HashMap<String, UserProfile>();

    public AccountServiceImpl() {
        addUser(new UserProfile("admin","admin","admin@admin.ru"));
        addUser(new UserProfile("dmitr","123","sorokin.dmitr@yandex.ru"));
    }

    public boolean addUser(UserProfile user) {
        if ( user.getLogin().equals("") || user.getPassword().equals("") || user.getEmail().equals("") ) {
            return false;
        } else {
            this.users.put(user.getLogin(), user);
            return true;
        }
    }

    public boolean addSession(String id, String login) {
        if ( !users.containsKey(login) )
            return false;
        if ( this.usersId.containsKey(login) ) {
            this.sessions.remove(this.usersId.get(login));
            this.usersId.remove(login);
        }
        this.sessions.put(id, users.get(login));
        this.usersId.put(login, id);
        return true;
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
