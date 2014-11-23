package base;

/**
 * Created by Vadim on 08.11.14.
 */
public class Ship {
    private int x0;
    private int y0;
    private int xn;
    private int yn;
    private int countDeck;

    public Ship(int x0, int y0, int xn, int yn) {
        // Ordering coordinates
        if (x0 >= xn && y0 >= yn) {
            // Swap points
            int bufX = x0;
            int bufY = y0;
            x0 = xn;
            y0 = yn;
            xn = bufX;
            yn = bufY;
        }

        this.x0 = x0;
        this.y0 = y0;
        this.xn = xn;
        this.yn = yn;
        calculateCountDeck();
    }

    private int calculateCountDeck() {
        if (this.x0 < 0 || this.y0 < 0) {
            countDeck = -1;
            return countDeck;
        }

        // The vertical orientation
        if (this.x0 == this.xn )
            countDeck = this.yn - this.y0 + 1;

        // The horizontal orientation
        else if (this.y0 == this.yn)
            countDeck = this.xn - this.x0 + 1;

        else
            this.countDeck = -1;

        return countDeck;
    }

    public int getX0() {
        return this.x0;
    }

    public int getY0() {
        return this.y0;
    }

    public int getXn() {
        return this.xn;
    }

    public int getYn() {
        return this.yn;
    }

    public int getCountDeck() {
        return this.countDeck;
    }

}