package mechanics;

import base.UserGame;

/**
 * Created by Dmitry on 025 25.10.14.
 */
public interface GameMechanics {

    void addUser(String user);

    UserGame getUserGame(String user);

    void incrementScore(String userName);

    void run();
}
