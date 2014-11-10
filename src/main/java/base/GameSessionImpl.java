package base;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmitry on 025 25.10.14.
 */
public class GameSessionImpl implements GameSession {
    private final long startTime;
    private final UserGame first;
    private final UserGame second;

    private Map<String, UserGame> users = new HashMap<>();

    public GameSessionImpl(String user1, String user2) {
        startTime = new Date().getTime();
        UserGame gameUser1 = new UserGameImpl(user1);
        gameUser1.setEnemyName(user2);

        UserGame gameUser2 = new UserGameImpl(user2);
        gameUser2.setEnemyName(user1);

        users.put(user1, gameUser1);
        users.put(user2, gameUser2);

        this.first = gameUser1;
        this.second = gameUser2;
    }

    public UserGame getEnemy(String user) {
        String enemyName = users.get(user).getEnemyName();
        return users.get(enemyName);
    }

    public UserGame getSelf(String user) {
        return users.get(user);
    }

    public long getSessionTime(){
        return new Date().getTime() - startTime;
    }

    public UserGame getFirst() {
        return first;
    }

    public UserGame getSecond() {
        return second;
    }

    public  boolean isFirstWin(){
        return first.getMyScore() > second.getMyScore();
    }
}