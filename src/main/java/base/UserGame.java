package base;

/**
 * Created by Dmitry on 025 25.10.14.
 */
public interface UserGame {

    public String getMyName();

    public String getEnemyName();

    public int getMyScore();

    public int getEnemyScore();

    public void incrementMyScore();

    public void incrementEnemyScore();

    public void setEnemyName(String enemyName);
}
