package sockets;

import base.UserGame;
import sockets.GameWebSocket;

/**
 * Created by Dmitry on 025 25.10.14.
 */
public interface WebSocketService {

    public void addUser(GameWebSocket user);

    public GameWebSocket getWebSocket(String login);

    public void notifyMyNewScore(UserGame user);

    public void notifyEnemyNewScore(UserGame user);

    public void notifyStartGame(UserGame user);

    public void notifyGameOver(UserGame user, boolean win);
}
