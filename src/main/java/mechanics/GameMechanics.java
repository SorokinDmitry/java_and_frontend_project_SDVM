package mechanics;


import java.util.ArrayList;

/**
 * Created by Vadim on 08.11.14.
 */

public interface GameMechanics {

    public boolean addUser(Long idUser);

    public void fire(Long idUser, int x, int y);

    public void run();
}
