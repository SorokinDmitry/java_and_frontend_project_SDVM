package mechanics;

import java.util.ArrayList;

/**
 * Created by Vadim on 08.11.14.
 */

public class Field {
    //private static final int FIELD_ROW_SIZE = 10;
    //private static final int FIELD_COL_SIZE = 10;

    private int rows;
    private int cols;
    private Cell[][] field;
    private ArrayList<Ship> ships = new ArrayList<>();
    private int numberNotFiredDecks;

    public Field(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.numberNotFiredDecks = 0;

        field = new Cell[rows][cols];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                field[i][j] = new Cell(i, j);
            }
        }

    }

    public int getNumberNotFiredDecks() {
        return this.numberNotFiredDecks;
    }

    public Cell getCell(int x, int y) {
        if (!coordinatesInField(x, y))
            return null;
        return field[x][y];
    }

    // ###
    public boolean setShip(Ship ship) {
        int countDeck = ship.getCountDeck();
        if (countDeck < 0)
            return false;

        for(int i = ship.getY0(); i <= ship.getYn(); ++i) {
            for (int j = ship.getX0(); j <= ship.getXn(); ++j) {
                if (!field[i][j].isAvailable())
                    return false;
                field[i][j].setDeck(true);
                field[i][j].setAccess(false);
                field[i][j].setShip(ship);
                ship.addCell(field[i][j]);
                numberNotFiredDecks++;
            }
        }

        // Окружение ячейки, чтобы нельзя было ставить корабли рядом
        for(int i = ship.getY0(); i <= ship.getYn(); ++i) {
            for (int j = ship.getX0(); j <= ship.getXn(); ++j) {
                surroundCell(i, j);
            }
        }

        ships.add(ship);
        return true;
    }

    public Codes fire(int x, int y) {
        if (!coordinatesInField(x, y))
            return Codes.CELL_DOES_NOT_EXIST;

        Cell cell = field[x][y];
        Codes status = cell.isKilled();

        if (status == Codes.DECK || status == Codes.KILLED) {
            numberNotFiredDecks--;
            if (getNumberNotFiredDecks() == 0)
                status = Codes.GAME_OVER;
        }
        cell.setFire(true);
        return status;
    }

    public boolean coordinatesInField(int x, int y) {
        return (x >= 0 && x < cols && y >= 0 && y < rows);
    }

    public void clearField() {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                this.field[i][j].setFire(false);
                this.field[i][j].setDeck(false);
                this.field[i][j].setAccess(true);
            }
        }
        this.numberNotFiredDecks = 0;
    }

    // В поле field вокруг ячейки Cell ячейки отмечаются как available=false;
    private void surroundCell(int x, int y) {
        if (x - 1 > 0) {
            field[x - 1][y].setAccess(false);
            if(y - 1 > 0)
                field[x - 1][y - 1].setAccess(false);
            if (y + 1 < rows)
                field[x - 1][y + 1].setAccess(false);
        }

        if (x + 1 < cols) {
            field[x + 1][y].setAccess(false);
            if(y - 1 > 0)
                field[x + 1][y - 1].setAccess(false);
            if (y + 1 < rows)
                field[x + 1][y + 1].setAccess(false);
        }

        if (y - 1 > 0)
            field[x][y - 1].setAccess(false);

        if (y + 1 < rows)
            field[x][y + 1].setAccess(false);
    }

}