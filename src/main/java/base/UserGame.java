package base;

import mechanics.Codes;
import mechanics.Field;
import mechanics.Ship;

import java.util.ArrayList;

/**
 * Created by Dmitry on 025 25.10.14.
 */
public interface UserGame {

    public String getMyName();

    public String getEnemyName();

    public Field getMyField();

    public Field getEnemyField();

    public Codes fireMyField(int x, int y);

    public Codes fireEnemyField(int x, int y);

    public void setMyField(Field myField);

    public void setEnemyField(Field enemyField);

    public void setEnemyName(String enemyName);

    public void setMyShips(ArrayList<Ship> ships);

    public void setEnemyShips(ArrayList<Ship> ships);

}
