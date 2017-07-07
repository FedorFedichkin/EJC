package main.java.task_03;

/**
* Class is made for boats creation and conserving its parameters.
* */
public class Boat {
    private int deckQuantity;
    private int xUpperLeftCoordinate;
    private int yUpperLeftCoordinate;
    private boolean isBoatHorizontal;
    private boolean isAlive;

    public Boat(int deckQuantity, int xUpperLeftCoordinate, int yUpperLeftCoordinate,
                boolean isBoatHorizontal, boolean isAlive) {
        this.deckQuantity = deckQuantity;
        this.xUpperLeftCoordinate = xUpperLeftCoordinate;
        this.yUpperLeftCoordinate = yUpperLeftCoordinate;
        this.isBoatHorizontal = isBoatHorizontal;
        this.isAlive = isAlive;
    }

    public int getxUpperLeftCoordinate() {
        return xUpperLeftCoordinate;
    }

    public int getyUpperLeftCoordinate() {
        return yUpperLeftCoordinate;
    }

    public int getDeckQuantity() {
        return deckQuantity;
    }

    public boolean isBoatHorizontal() {
        return isBoatHorizontal;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
