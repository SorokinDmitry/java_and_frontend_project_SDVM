package mechanics;


import base.UserGame;

import java.util.ArrayList;

/**
 * Created by Vadim on 08.11.14.
 */

public interface GameMechanics {

    public boolean addUser(String emailUser);

    public Codes fire(String user, int x, int y);

    public UserGame getUserGame(String emailUser);

    public Codes setShips(String emailUser, ArrayList<Ship> ships);

    public void closeGame(String emailUser);

    public int getCountGameSessions();

    public int getCountUsersInQueue();
    //public void run();
}
