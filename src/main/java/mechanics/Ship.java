package mechanics;

/**
 * Created by Vadim on 30.11.14.
 */
public interface Ship {
    public int getX0();
    public int getY0();
    public int getXn();
    public int getYn();
    public int getCountDeck();
    public boolean isKilled();
    public void kill();
    public void addCell(Cell cell);
}
