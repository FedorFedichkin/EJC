package main.java.task_03;

/**
* Class is made for boats creation and conserving its parameters.
* */
public class Boat {
    private int deckQuantity;
    private int upperLeftCoordinateX;
    private int upperLeftCoordinateY;
    private boolean isBoatHorizontal;
    private boolean isAlive;

    public Boat(int deckQuantity, int upperLeftCoordinateX, int upperLeftCoordinateY,
                boolean isBoatHorizontal, boolean isAlive) {
        this.deckQuantity = deckQuantity;
        this.upperLeftCoordinateX = upperLeftCoordinateX;
        this.upperLeftCoordinateY = upperLeftCoordinateY;
        this.isBoatHorizontal = isBoatHorizontal;
        this.isAlive = isAlive;
    }

    public int getUpperLeftCoordinateX() {
        return upperLeftCoordinateX;
    }

    public int getUpperLeftCoordinateY() {
        return upperLeftCoordinateY;
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
