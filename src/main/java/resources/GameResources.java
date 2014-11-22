package resources;

import java.io.Serializable;

/**
 * Created by Dmitry on 010 10.11.14.
 */
public class GameResources implements Serializable {
    private static final long serialVersionUID = -3895203507200457732L;
    private int gameTime;
    private int fieldSize;
    private int countOfShips;

    public GameResources() {
        this.fieldSize = 10;
        this.countOfShips = 10;
        this.gameTime = 15 * 1000;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public int getGameTime() {
        return gameTime;
    }

    public void setFieldSize(int fieldSize) {
        this.fieldSize = fieldSize;
    }

    public int getCountOfShips() {
        return this.countOfShips;
    }

    public void setCountOfShips(int fieldSize) {
        this.countOfShips = fieldSize;
    }

    public int getFieldSize() {
        return this.fieldSize;
    }

    public String toString() {
        return "GameResources: " + fieldSize + ", " + countOfShips;
    }
}