package mechanics;


import java.util.ArrayList;

/**
 * Created by Vadim on 08.11.14.
 */

public interface GameMechanics {

    public boolean addUser(String user);

    public Codes fire(String user, int x, int y);

    public void run();
}
