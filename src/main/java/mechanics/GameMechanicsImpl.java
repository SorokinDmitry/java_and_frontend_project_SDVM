package mechanics;

import sockets.WebSocketService;
import utils.TimeHelper;

import java.util.*;

/**
 * Created by Vadim on 09.11.14.
 */
public class GameMechanicsImpl implements GameMechanics {
    private static final int STEP_TIME = 100;
    private WebSocketService webSocketService;

    private Queue<String> queueUsers = new LinkedList<>();
    private Map<String, GameSession> gameSessionsMap = new HashMap<>();
    private Set<GameSession> allGameSessions = new HashSet<>();

    private String waiter;

    public GameMechanicsImpl(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    public void run() {
        System.out.append("Game Mechanics Run");
        while (true) {
            gmStep();
            TimeHelper.sleep(STEP_TIME);
        }
    }

    public boolean addUser(String user) {
        if (waiter != null) {
            startGame(user);
            waiter = null;
        } else {
            waiter = user;
        }

        return true;
        //if (userInQueue(idUser))
        //    return false;
        //return queueUsers.add(idUser);
    }

    public Codes fire(String user, int x, int y) {
        return gameSessionsMap.get(user).fire(user, x, y);
    }

    public void setShips(String user, ArrayList<Ship> ships) {
        GameSession gameSession = gameSessionsMap.get(user);
        gameSession.setShips(user, ships);
    }

    private boolean userInQueue(String user) {
        return queueUsers.contains(user);
    }

    private void startGame(String user1) {
        String user2 = waiter;
        GameSession gameSession = createGameSession(user1, user2);

        //Вот так должна начинаться игра!
        webSocketService.notifyStartGame(user1, user2);
    }

    private void gmStep() {
        for (GameSession session : allGameSessions) {
            if (session.isGameOver()) {
                boolean firstWin = session.isWinner(session.getFirst());

                //webSocketService.notifyGameOver(session.getFirst(), firstWin);
                //webSocketService.notifyGameOver(session.getSecond(), !firstWin);

                closeGameSession(session.getFirst(), session.getSecond());
            }
        }
    }


    public GameSession getGameSession(String user) {
        return gameSessionsMap.get(user);
    }

    private GameSession createGameSession(String user1, String user2) {
        GameSession gameSession = new GameSessionImpl(user1, user2);
        gameSessionsMap.put(user1, gameSession);
        gameSessionsMap.put(user2, gameSession);
        allGameSessions.add(gameSession);
        return gameSession;
    }

    private void closeGameSession(String user1, String user2) {
        GameSession gameSession = gameSessionsMap.remove(user1);
        gameSessionsMap.remove(user2);
        allGameSessions.remove(gameSession);
    }

    public int getCountGameSessions() {
        return allGameSessions.size();
    }

    public int getCountUsersInQueue() {
        return queueUsers.size();
    }
}
