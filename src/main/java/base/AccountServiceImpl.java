package base;

import utils.UserDataSet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmitry on 013 13.09.14.
 */
public class AccountServiceImpl implements AccountService {
    private Map<String, UserDataSet> users = new HashMap<String, UserDataSet>();
    private Map<String, String> usersId = new HashMap<String, String>();
    private Map<String, UserDataSet> sessions = new HashMap<String, UserDataSet>();

    public AccountServiceImpl() {
        addUser("admin","admin","admin@admin.ru");
        addUser("dmitr","123","sorokin.dmitr@yandex.ru");
    }

    public boolean addUser(String login, String password, String email) {
        if ( login.isEmpty() || password.isEmpty() || email.isEmpty() ) {
            return false;
        } else {
            UserDataSet user = new UserDataSet(login, password, email);
            this.users.put(login, user);
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

    public String getUserEmailBySessionId(String id) {
        if (sessions.containsKey(id)) {
            return sessions.get(id).getEmail();
        } else {
            return null;
        }
    }

    public String getUserLoginBySessionId(String id) {
        if (sessions.containsKey(id)) {
            return sessions.get(id).getLogin();
        } else  {
            return null;
        }
    }
}
