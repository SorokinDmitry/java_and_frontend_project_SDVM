package base;

/**
 * Created by Dmitry on 025 25.10.14.
 */
public class UserGameImpl implements UserGame{
    private final String myName;
    private String enemyName;
    private int myScore = 0;
    private int enemyScore = 0;

    public UserGameImpl(String myName) {
        this.myName = myName;
    }

    public String getMyName() {
        return myName;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public int getMyScore() {
        return myScore;
    }

    public int getEnemyScore() {
        return enemyScore;
    }

    public void incrementMyScore() {
        myScore++;
    }

    public void incrementEnemyScore() {
        enemyScore++;
    }

    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }
}
