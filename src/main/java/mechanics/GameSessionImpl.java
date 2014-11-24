package mechanics;

import java.util.ArrayList;

/**
 * Created by Vadim on 09.11.14.
 */

public class  GameSessionImpl implements GameSession {
    private static final int FIELD_ROW_SIZE = 10;
    private static final int FIELD_COL_SIZE = 10;

    private String user1;
    private String user2;
    private Field fieldUser1;
    private Field fieldUser2;

    public String getFirst() {
        return this.user1;
    }

    public String getSecond() {
        return this.user2;
    }

    public GameSessionImpl(String user1, String user2) {
        this.user1 = user1;
        this.user2 = user2;
        //fieldUser1 = null;
        //fieldUser2 = null;

        Ship fourDecker1 = new Ship(0, 0, 3, 0);

        Ship threeDecker1 = new Ship(5, 0, 7, 0);
        Ship threeDecker2 = new Ship(0, 2, 2, 2);

        Ship twoDecker1 = new Ship(4, 2, 5, 2);
        Ship twoDecker2 = new Ship(7, 2, 8, 2);
        Ship twoDecker3 = new Ship(0, 4, 1, 4);

        Ship oneDecker1 = new Ship(3, 4, 3, 4);
        Ship oneDecker2 = new Ship(5, 4, 5, 4);
        Ship oneDecker3 = new Ship(7, 4, 7, 4);
        Ship oneDecker4 = new Ship(9, 4, 9, 4);

        ArrayList<Ship> ships = new ArrayList<>();
        ships.add(fourDecker1);
        ships.add(threeDecker1);
        ships.add(threeDecker2);
        ships.add(twoDecker1);
        ships.add(twoDecker2);
        ships.add(twoDecker3);
        ships.add(oneDecker1);
        ships.add(oneDecker2);
        ships.add(oneDecker3);
        ships.add(oneDecker4);

        this.setShips(user1, ships);
        this.setShips(user2, ships);

    }

    public Codes setShips(String user, ArrayList<Ship> ships) {
        if (!userInSession(user))
            return Codes.USER_NOT_FOUND;

        // If field != null ???

        // Здесь должна быть проверка на корректность расстановки кораблей

        Field field;
        // Расстановка кораблей
        field = new Field(FIELD_ROW_SIZE, FIELD_COL_SIZE);

        for (Ship ship : ships) {
            if (!field.setShip(ship)) {
                field.clearField();
                return Codes.ERROR;
            }
        }

        if (user == user1)
            fieldUser1 = field;
        else
            fieldUser2 = field;

        return Codes.OK;
    }

    public Codes fire(String user, int x, int y) {
        Field field = getField(getIdOpponent(user));
        return field.fire(x, y);
    }

    private int getNumberNotFiredDecks(String user) {
        if (!userInSession(user))
            return -1;
        Field field;
        if (user.equals(user1))
            field = fieldUser1;
        else
            field = fieldUser2;

        return field.getNumberNotFiredDecks();
    }

    private String getIdOpponent(String user) {
        if (!userInSession(user))
            return null;
        if (user.equals(user1))
            return user2;
        return user1;
    }

    public Field getField(String user) {
        if (user.equals(user1))
            return fieldUser1;
        if (user.equals(user2))
            return fieldUser2;
        return null;
    }

    private boolean userInSession(String user) {
        return  (user.equals(user1) || (user.equals(user2)));
    }

    public boolean isGameOver() {
        if (getNumberNotFiredDecks(this.user1) == 0) {
            return true;
        }
        if (getNumberNotFiredDecks(this.user2) == 0) {
            return true;
        }
        return false;
    }

    public boolean isWinner(String user) {
        if (getNumberNotFiredDecks(getIdOpponent(user)) == 0)
            return true;
        return false;
    }

}
