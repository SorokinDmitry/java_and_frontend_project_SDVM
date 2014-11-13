package mechanics;

/**
 * Created by Dmitry on 025 25.10.14.
 */
public interface GameMechanics {

    public void addUser(String user);

    public void incrementScore(String userName);

    public void run();
}
