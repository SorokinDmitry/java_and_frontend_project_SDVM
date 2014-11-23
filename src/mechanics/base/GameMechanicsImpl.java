package base;

// импорты могут быть неверными!
    import base.GameSession;
    import base.GameSessionImpl;

    import java.util.*;

/**
 * Created by Vadim on 09.11.14.
 */
public class GameMechanicsImpl implements GameMechanics {
    private static final int STEP_TIME = 100;
    private WebSocketService webSocketService;

    Queue<Long> queueUsers = new LinkedList<Long>();
    Map<Long, GameSession> gameSessionsMap = new HashMap<>();
    private Set<GameSession> allGameSessions = new HashSet<>();

    private Long waiter;

    public GameMechanicsImpl(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    //@Override
    public void run() {
        System.out.append("Game Mechanics Run");
        while (true) {
            gmStep();
            TimeHelper.sleep(STEP_TIME);
        }
    }

    public boolean addUser(long idUser) {
        if (waiter != null) {
            startGame(idUser);
            waiter = null;
        } else {
            waiter = idUser;
        }

        return true;
        //if (userInQueue(idUser))
        //    return false;
        //return queueUsers.add(idUser);
    }

    public Codes fire(long idUser, int x, int y) {
        return gameSessionsMap.get(idUser).fire(idUser, x, y);
    }

    public void setShips(long idUser, ArrayList<Ship> ships) {
        GameSession gameSession = gameSessionsMap.get(idUser);
        gameSession.setShips(idUserm ships);
    }

    private boolean userInQueue(long idUser) {
        return queueUsers.contains(idUser);
    }

    private void startGame(long idUser1) {
        Long idUser2 = waiter;
        GameSession gameSession = createGameSession(long idUser1, idUser2);

        //Вот так должна начинаться игра!
        //webSocketService.notifyStartGame(gameSession.getSelf(idUser1));
        //webSocketService.notifyStartGame(gameSession.getSelf(idUser2));
    }

    private void gmStep() {
        for (GameSession session : allGameSessions) {
            if (session.isGameOver()) {
                boolean firstWin = session.isWinner(session.getFirst());

                webSocketService.notifyGameOver(session.getFirst(), firstWin);
                webSocketService.notifyGameOver(session.getSecond(), !firstWin);

                closeGameSession(session.getFirst(), session.getSecond());
            }
        }
    }



    public GameSession getGameSession(long idUser) {
        return gameSessionsMap.get(idUser);
    }


    private GameSession createGameSession(long idUser1, idUser2) {
        GameSession gameSession = new GameSessionImpl(idUser1, idUser2);
        gameSessionsMap.put(idUser1, gameSession);
        gameSessionsMap.put(idUser2, gameSession);
        allGameSessions.add(gameSession);
        return gameSession;
    }

    private void closeGameSession(long idUser1, long idUser2) {
        GameSession gameSession = gameSessionsMap.remove(idUser1);
        gameSessionsMap.remove(idUser2);
        allGameSessions.remove(gameSession);
    }

    public int getCountGameSessions() {
        return allGameSessions.size();
    }

    public int getCountUsersInQueue() {
        return queueUsers.size();
    }
}
