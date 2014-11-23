package base;

import base.Codes;
import base.Field;
import base.Ship;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Vadim on 09.11.14.
 */

// Проверка на победителя гдеее???
public class GameSessionImpl implements GameSession {
    private static final int FIELD_ROW_SIZE = 10;
    private static final int FIELD_COL_SIZE = 10;

    private long idUser1;
    private long idUser2;
    private Field fieldUser1;
    private Field fieldUser2;

    public long getFirst() {
        return this.idUser1;
    }

    public long getSecond() {
        return this.idUser2;
    }

    public GameSession(long idUser1, long idUser2) {
        this.idUser1 = idUser1;
        this.idUser2 = idUser2;
        fieldUser1 = null;
        fieldUser2 = null;
    }

    public Codes setShips(long idUser, ArrayList<Ship> ships) {
        if (!userInSession(idUser))
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

        if (idUser == idUser1)
            fieldUser1 = field;
        else
            fieldUser2 = field;

        return Codes.OK;
    }

    public Codes fire(long idUser, int x, int y) {
        Field field = getField(getIdOpponent(idUser));
        return field.fire(x, y);
    }

    public int getNumberNotFiredDecks(long idUser) {
        if (!userInSession(idUser))
            return -1;
        Field field;
        if (idUser == idUser1)
            field = fieldUser1;
        else
            field = fieldUser2;

        return field.getNumberNotFiredDecks();
    }

    public long getIdOpponent(long idUser) {
        if (!userInSession(idUser))
            return -1;
        if (idUser == idUser1)
            return idUser2;
        return idUser1;
    }

    public Field getField(long idUser) {
        if (idUser == idUser1)
            return fieldUser1;
        if (idUser == idUser2)
            return fieldUser2;
        return null;
    }

    public boolean userInSession(long idUser) {
        return  (idUser == idUser1) || (idUser == idUser2);
    }

    public boolean isGameOver() {
        if (getNumberNotFiredDecks(this.idUser1) == 0) {
            return true;
        }
        if (getNumberNotFiredDecks(this.idUser2) == 0) {
            return true;
        }
        return false;
    }

    public boolean isWinner(long idUser) {
        if (getNumberNotFiredDecks(getIdOpponent(idUser));
            return true;
        return false;
    }

    public boolean isFirstWin() {

    }

}
