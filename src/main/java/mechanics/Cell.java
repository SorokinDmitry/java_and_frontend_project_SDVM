package mechanics;

/**
 * Created by Vadim on 30.11.14.
 */

public interface Cell {
    public int getX();
    public void setX(int x);

    public int getY();
    public void setY(int y);

    public void setXY(int x, int y);

    public boolean isFired();
    public boolean isDeck();
    public boolean isAvailable();

    public void setFire(boolean fired);
    public void setDeck(boolean ship);
    public void setAccess(boolean available);
    public void setShip(Ship ship);

    public Codes isKilled();
    public void clearCell();
    public void killShip();
}
