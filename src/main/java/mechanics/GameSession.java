package mechanics;

import base.UserGame;

import java.util.ArrayList;

/**
 * Created by Vadim on 23.11.14.
 */

public interface GameSession {
    public UserGame getFirst();
    public UserGame getSecond();

    public UserGame getUserGameByEmail(String emailUsr);

    public Codes fire(UserGame user, int x, int y);
    public Field getField(String user);
    public Codes setShips(String user, ArrayList<Ship> ships);
}