package base;

/**
 * Created by Vadim on 08.11.14.
 */
public class Field {
    //private static final int FIELD_ROW_SIZE = 10;
    //private static final int FIELD_COL_SIZE = 10;

    private int rows;
    private int cols;
    private Cell[][] field;
    private int numberNotFiredDecks;

    public Field(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.numberNotFiredDecks = 0;

        field = new Cell[rows][cols];

        System.out.print(field[0][0]);
        System.out.print("rows: " + rows + " cols: " + cols + "\n");
        //field[0][0].isFire();

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (field[i][j] == null)
                    System.out.print("i: " + i + " j: " + j);
                field[i][j].setXY(i, j);
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

    public boolean setShip(Ship ship) {
        int countDeck = ship.getCountDeck();
        if (countDeck< 0)
            return false;

        // Проверкаааа!!!

        for(int i = ship.getX0(); i <= ship.getXn(); ++i) {
            for (int j = ship.getY0(); j <= ship.getYn(); ++j) {
                field[i][j].setDeck(true);
                numberNotFiredDecks++;
            }
        }

        return true;
    }

    public Codes fire(int x, int y) {
        if (!coordinatesInField(x, y))
            return Codes.CELL_DOES_NOT_EXIST;

        Cell cell = field[x][y];
        if (cell.isFire())
            return Codes.IS_FIRED;

        if (cell.isDeck()) {
            cell.setFire(true);
            return Codes.DECK;

        }

        cell.setFire(true);
        return Codes.EMPTY;
    }

    public boolean coordinatesInField(int x, int y) {
        return (x >= 0 && x < cols && y >= 0 && y < rows);
    }


    // Метод для отладки
    public void printField() {
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                System.out.print(field[i][j].isDeck() + " ");
            }
            System.out.println();
        }
    }

    /*
    private Ship singleDecker;
    private Ship twoDecker;
    private Ship threeDecker;
    private Ship fourDecker;
    */
    /*
    fourDecker - battleship
    threeDecker - cruiser
    twoDecker - destroyer
    singleDecker - submarine
     */
}
