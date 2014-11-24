package mechanics;

import java.util.ArrayList;

/**
 * Created by Vadim on 23.11.14.
 */

public interface GameSession {
    public String getFirst();
    public String getSecond();
    public Field getField(String user);
    public Codes setShips(String user, ArrayList<Ship> ships);
    public Codes fire(String user, int x, int y);
    public boolean isWinner(String user);
    public boolean isGameOver();
}