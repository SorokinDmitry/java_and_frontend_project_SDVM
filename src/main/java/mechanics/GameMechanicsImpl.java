package mechanics;

import base.GameSession;
import base.GameSessionImpl;
import base.UserGame;
import resources.ResourceFactory;
import sockets.WebSocketService;
import utils.TimeHelper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Dmitry on 025 25.10.14.
 */
public class GameMechanicsImpl implements GameMechanics {
    private static final int STEP_TIME = 100;

    //private static final int gameTime = 15 * 1000;

    private WebSocketService webSocketService;

    private Map<String, GameSession> nameToGame = new HashMap<>();

    private Set<GameSession> allSessions = new HashSet<>();

    private String waiter;

    public GameMechanicsImpl(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    public void addUser(String user) {
        if ( !nameToGame.containsKey(user) && (waiter == null || !waiter.equals(user)))
            if (waiter != null) {
                starGame(user);
                waiter = null;
            } else {
                waiter = user;
            }
    }

    public UserGame getUserGame(String user) {
        if ( nameToGame.containsKey(user) )
            return  nameToGame.get(user).getSelf(user);
        else
            return null;
    }

    public void incrementScore(String userName) {
        GameSession myGameSession = nameToGame.get(userName);
        UserGame myUser = myGameSession.getSelf(userName);
        myUser.incrementMyScore();
        UserGame enemyUser = myGameSession.getEnemy(userName);
        enemyUser.incrementEnemyScore();
        webSocketService.notifyMyNewScore(myUser);
        webSocketService.notifyEnemyNewScore(enemyUser);
    }

    @Override
    public void run() {
        System.out.append("GameMechanics -> run");
        while (true) {
            gmStep();
            TimeHelper.sleep(STEP_TIME);
        }
    }

    private void gmStep() {
        for (GameSession session : allSessions) {
            if ( session.getSessionTime() > ResourceFactory.getInstance().getGameResources().getGameTime() ) {
                boolean firstWin = session.isFirstWin();

                webSocketService.notifyGameOver(session.getFirst(), firstWin);
                webSocketService.notifyGameOver(session.getSecond(), !firstWin);
                nameToGame.remove(session.getFirst().getMyName());
                nameToGame.remove(session.getSecond().getMyName());
                allSessions.remove(session);
            }
        }
    }

    private void starGame(String first) {
        String second = waiter;
        GameSession gameSession = new GameSessionImpl(first, second);
        allSessions.add(gameSession);
        nameToGame.put(first, gameSession);
        nameToGame.put(second, gameSession);

        webSocketService.notifyStartGame(gameSession.getSelf(first));
        webSocketService.notifyStartGame(gameSession.getSelf(second));
    }
}
