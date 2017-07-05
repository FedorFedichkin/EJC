package main.java.task_03;

import java.util.Random;

public class Boat {
    private int deckQuantity;
    private int xUpperLeftCoordinate;
    private int yUpperLeftCoordinate;
    private boolean isBoatHorizontal;
    private boolean isAlive;
    private final static int MAX_DECK_NUMBER = 8;

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

    public void setxUpperLeftCoordinate(int xUpperLeftCoordinate) {
        this.xUpperLeftCoordinate = xUpperLeftCoordinate;
    }

    public int getyUpperLeftCoordinate() {
        return yUpperLeftCoordinate;
    }

    public void setyUpperLeftCoordinate(int yUpperLeftCoordinate) {
        this.yUpperLeftCoordinate = yUpperLeftCoordinate;
    }

    public int getDeckQuantity() {
        return deckQuantity;
    }

    public void setDeckQuantity(int deckQuantity) {
        this.deckQuantity = deckQuantity;
    }

    public boolean isBoatHorizontal() {
        return isBoatHorizontal;
    }

    public void setBoatHorizontal(boolean boatHorizontal) {
        isBoatHorizontal = boatHorizontal;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

}
