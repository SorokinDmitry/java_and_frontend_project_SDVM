package base;

import dataSets.UserDataSet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by serg on 21.11.14.
 */
public class AccountServiceDBImpl implements AccountService {
    private DatabaseService databaseService;
    private Map<String, String> usersSessions = new HashMap<String, String>();
    private Map<String, UserDataSet> sessions = new HashMap<String, UserDataSet>();

    public AccountServiceDBImpl(DatabaseService databaseService) {
        this.databaseService = databaseService;
        addUser("admin","admin","admin@admin.ru");
        addUser("dmitr","123","sorokin.dmitr@yandex.ru");
    }

    public boolean addUser(String login, String password, String email) {
        if ( login.isEmpty() || password.isEmpty() || email.isEmpty()
                || databaseService.checkUserRegistered(login)) {
            return false;
        } else {
            databaseService.addUser(login, password, email);
            return true;
        }
    }

    public boolean addSession(String id, String login) {
        if ( !this.haveUser(login) )
            return false;
        UserDataSet user;
        if ( this.usersSessions.containsKey(login) ) {
            user = sessions.get(this.usersSessions.get(login));
            this.sessions.remove(this.usersSessions.get(login));
            this.usersSessions.remove(login);
        } else {
            if ( this.sessions.containsKey(id) ) {
                this.usersSessions.remove(this.sessions.get(id).getLogin());
                this.sessions.remove(id);
            }
            user = databaseService.getUser(login);
        }
        this.sessions.put(id, user);
        this.usersSessions.put(login, id);
        return true;
    }

    public int getCountOfUsers() {
        return (int)databaseService.countOfUsers();
    }

    public int getCountOfSessions() {
        return this.sessions.size();
    }

    public void deleteSession(String id) {
        this.usersSessions.remove(this.sessions.get(id).getLogin());
        this.sessions.remove(id);
    }

    public boolean haveSession(String id) {
        return  sessions.containsKey(id);
    }

    public boolean haveUser(String login) {
        return  databaseService.checkUserRegistered(login);
    }

    public boolean isPasswordCorrect(String login, String password) {
        UserDataSet user = databaseService.getUser(login);
        return password.equals(user.getPassword());
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
