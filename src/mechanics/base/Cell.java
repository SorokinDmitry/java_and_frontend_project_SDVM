package base;

/**
 * Created by Vadim on 08.11.14.
 */
public class Cell {
    private int x;
    private int y;
    private boolean fire;
    private boolean deck;
    //private boolean accessToPlace;

    public Cell() {
        this.x = -1;
        this.y = -1;
        this.fire = false;
        this.deck = false;
        //this.accessToPlace = true;
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.fire = false;
        this.deck = false;
        //this.accessToPlace = true;
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


    public boolean isFire() {
        return fire;
    }
    public boolean isDeck() {
        return deck;
    }

    public void setFire(boolean fire) {
        this.fire = fire;
    }
    public void setDeck(boolean ship) {
        this.deck = ship;
    }


}
