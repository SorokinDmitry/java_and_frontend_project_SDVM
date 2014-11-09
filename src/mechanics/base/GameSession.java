package base;

import base.Codes;
import base.Field;
import base.Ship;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Vadim on 09.11.14.
 */
public class GameSession {
    private static final int FIELD_ROW_SIZE = 10;
    private static final int FIELD_COL_SIZE = 10;

    private long idUser1;
    private long idUser2;
    private Field fieldUser1;
    private Field fieldUser2;

    public GameSession(long idUser1, long idUser2) {
        this.idUser1 = idUser1;
        this.idUser2 = idUser2;
        fieldUser1 = null;
        fieldUser2 = null;
    }

    public Codes placeShips(ArrayList<Ship> ships, long idUser) {
        if (!userInSession(idUser))
            return Codes.USER_NOT_FOUND;

        // Если field != null ???
        Field field;
        if (idUser == idUser1)
            field = fieldUser1;
        else
            field = fieldUser2;

        if (field != null)
            return Codes.FIELD_EXIST;

        // Здесь должна быть проверка на корректность расстановки кораблей

        // Расстановка кораблей
        field = new Field(FIELD_ROW_SIZE, FIELD_COL_SIZE);
        for (Iterator<Ship> iterator = ships.iterator(); iterator.hasNext(); ) {
            Ship ship = iterator.next();
            field.setShip(ship);
        }

        return Codes.OK;
    }

    public Codes fire(long idUser, int x, int y) {
        // Провера на userInSession не нужна
        Field field = getField(idUser);


        return Codes.CELL_DOES_NOT_EXIST;
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

}
