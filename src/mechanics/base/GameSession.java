package base;

import java.util.ArrayList;

/**
 * Created by Vadim on 23.11.14.
 */

public interface GameSession {
    public long getFirst();
    public long getSecond();
    public Field getField(long idUser);
    public Codes setShips(long idUser, ArrayList<Ship> ships);
    public Codes fire(long idUser, int x, int y);
    public boolean isWinner(long idUser);
    public boolean isGameOver();
}