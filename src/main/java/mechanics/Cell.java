package mechanics;

/**
 * Created by Vadim on 08.11.14.
 */
public class Cell {
    private int x;
    private int y;
    private boolean fired;
    private boolean deck;
    private boolean available; // Доступ для размещения кораблей
    private Ship ship;


    public Cell() {
        this.x = -1;
        this.y = -1;
        this.fired = false;
        this.deck = false;
        this.available = true;
        this.ship = null;
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.fired = false;
        this.deck = false;
        this.available = true;
    }


    public int getX() {
        return this.x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public boolean isFired() {
        return fired;
    }
    public boolean isDeck() {
        return deck;
    }
    public boolean isAvailable() {
        return available;
    }

    public void setFire(boolean fired) {
        this.fired = fired;
    }
    public void setDeck(boolean ship) {
        this.deck = ship;
    }
    public void setAccess(boolean available) {
        this.available = available;
    }
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Codes isKilled() {
        if (this.fired == true) {
            return Codes.IS_FIRED;
        }

        if (this.deck == true) {
            if (ship.isKilled()) {
                return Codes.KILLED;
            }
            else return Codes.DECK;
        }

        return Codes.EMPTY;
    }

}
