package base;

/**
 * Created by Vadim on 23.11.14.
 */

public interface GameSession {

    public UserGame getEnemy(String user);

    public UserGame getSelf(String user);

    public long getSessionTime();

    public UserGame getFirst();

    public UserGame getSecond();

    public  boolean isFirstWin();

}