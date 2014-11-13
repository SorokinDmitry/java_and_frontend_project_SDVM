package base;

/**
 * Created by Dmitry on 025 25.10.14.
 */
public interface GameSession {

    public UserGame getEnemy(String user);

    public UserGame getSelf(String user);

    public long getSessionTime();

    public UserGame getFirst();

    public UserGame getSecond();

    public  boolean isFirstWin();

}
