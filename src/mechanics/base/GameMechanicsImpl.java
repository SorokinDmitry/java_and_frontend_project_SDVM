package base;

import java.util.*;

/**
 * Created by Vadim on 09.11.14.
 */
public class GameMechanicsImpl {
    Queue<Long> queueUsers = new LinkedList<Long>();
    Map<Long, GameSession> gameSessionsMap = new HashMap<>();


    public boolean addUserToQueue(long idUser) {
        if (userInQueue(idUser))
            return false;
        return queueUsers.add(idUser);
    }

    public boolean userInQueue(long idUser) {
        return queueUsers.contains(idUser);
    }


    public GameSession getGameSession(long idUser) {
        return gameSessionsMap.get(idUser);
    }


    private GameSession createGameSession(long idUser1, long idUser2) {
        // Не проверяет на уникальность idUser1, idUser2 (и не надо)
        GameSession gameSession = new GameSession(idUser1, idUser2);
        gameSessionsMap.put(idUser1, gameSession);
        gameSessionsMap.put(idUser2, gameSession);
        return gameSession;
    }

    public void closeGameSession(long idUser1, long idUser2) {
        gameSessionsMap.remove(idUser1);
        gameSessionsMap.remove(idUser2);
    }

    public int getCountGameSessions() {
        return gameSessionsMap.size() / 2;
    }

    public int getCountUsersInQueue() {
        return queueUsers.size();
    }
}
