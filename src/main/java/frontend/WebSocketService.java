package frontend;

import base.UserGame;

/**
 * Created by Dmitry on 025 25.10.14.
 */
public interface WebSocketService {

    public void addUser(GameWebSocket user);

    public void notifyMyNewScore(UserGame user);

    public void notifyEnemyNewScore(UserGame user);

    public void notifyStartGame(UserGame user);

    public void notifyGameOver(UserGame user, boolean win);
}
