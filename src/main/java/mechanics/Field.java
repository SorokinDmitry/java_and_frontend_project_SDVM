package mechanics;

/**
 * Created by Vadim on 30.11.14.
 */
public interface Field {
    public int getNumberNotFiredDecks();
    public Cell getCell(int x, int y);
    public boolean setShip(Ship ship);
    public Codes fire(int x, int y);
    public boolean coordinatesInField(int x, int y);
    public void clearField();
}
