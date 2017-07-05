package main.java.task_03;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainLogic {
    private final static int FIELD_SIZE = 10;
    private int[][] fieldWithData = new int[FIELD_SIZE][FIELD_SIZE];
    private int[][] demonstratedField = new int[FIELD_SIZE][FIELD_SIZE];
    private List<Boat> boats = new ArrayList<>();

    public void generateEmptyField(){
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                this.fieldWithData[i][j] = 0;
            }
        }
    }

    public int[][] getCurrentStateOfField(){
        return this.fieldWithData;
    }

    public void putBoatOnField(int deckQuantity){
        int maxNumberOfBoatDecks = FIELD_SIZE - 2;

        if (deckQuantity > maxNumberOfBoatDecks){
            System.out.println("The boat with " + deckQuantity +
                    " decks is too large for the fieldWithData of size of " + FIELD_SIZE + " cells.");
            System.out.println("Max possible number of boat decks is " + maxNumberOfBoatDecks);
        }

        Random random = new Random();
        boolean isBoatHorizontal;
        int xUpperLeft;
        int yUpperLeft;

        do {
            isBoatHorizontal = random.nextBoolean();
            if (isBoatHorizontal){
                xUpperLeft = random.nextInt(FIELD_SIZE - deckQuantity - 1) + 1;
                yUpperLeft = random.nextInt(maxNumberOfBoatDecks) + 1;
            } else {
                xUpperLeft = random.nextInt(maxNumberOfBoatDecks) + 1;
                yUpperLeft = random.nextInt(FIELD_SIZE - deckQuantity - 1) + 1;
            }

        } while (!isPossibleToPutBoatOnField(xUpperLeft, yUpperLeft, deckQuantity, isBoatHorizontal));

        boats.add(new Boat(deckQuantity, xUpperLeft, yUpperLeft, isBoatHorizontal, true));

        if (isBoatHorizontal){
            for (int i = xUpperLeft; i < xUpperLeft + deckQuantity; i++) {
                this.fieldWithData[yUpperLeft][i] = 1;
            }
        } else {
            for (int i = yUpperLeft; i < yUpperLeft + deckQuantity; i++) {
                this.fieldWithData[i][xUpperLeft] = 1;
            }
        }
    }

    private boolean isPossibleToPutBoatOnField(int xUpperLeftCoordinate, int yUpperLeftCoordinate,
                                                      int deckQuantity, boolean isBoatHorizontal){
        if (isBoatHorizontal) {
            if ((xUpperLeftCoordinate + deckQuantity) > FIELD_SIZE) {
                return false;
            } else {
                for (int i = 0; i < deckQuantity; i++) {
                    for (int j = xUpperLeftCoordinate - 1; j < xUpperLeftCoordinate + 2; j++) {
                        for (int k = yUpperLeftCoordinate - 1; k < yUpperLeftCoordinate + 2; k++) {
                            if (k < FIELD_SIZE && (j+i) < FIELD_SIZE && this.fieldWithData[k][j+i] != 0) return false;
                        }
                    }
                }
            }
        } else {
            if ((yUpperLeftCoordinate + deckQuantity) > FIELD_SIZE) {
                return false;
            } else {
                for (int i = 0; i < deckQuantity; i++) {
                    for (int j = xUpperLeftCoordinate - 1; j < xUpperLeftCoordinate + 2; j++) {
                        for (int k = yUpperLeftCoordinate - 1; k < yUpperLeftCoordinate + 2; k++) {
                            if (j < FIELD_SIZE && (k+i) < FIELD_SIZE && this.fieldWithData[k+i][j] != 0) return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean checkIfHit(int x, int y){
        return this.fieldWithData[y-1][x-1] > 0;
    }

    public boolean checkIfDestroyed(int x, int y){
        int deckQuantity;
        boolean isBoatHorizontal;
        for (Boat boat: boats) {
            if(boat.isAlive()){
                isBoatHorizontal = boat.isBoatHorizontal();
                deckQuantity = boat.getDeckQuantity();
                if(isBoatHorizontal){
                    int xUpperLeftCoordinate = boat.getxUpperLeftCoordinate();
                    int yUpperLeftCoordinate = boat.getyUpperLeftCoordinate();
                    int count = 0;
                    boolean isThisBoat = false;

                    for (int i = xUpperLeftCoordinate; i < xUpperLeftCoordinate + deckQuantity; i++) {
                        if (fieldWithData[y-1][i] == 2){
                            count++;
                            if(fieldWithData[yUpperLeftCoordinate][i] == fieldWithData[y-1][x-1]){
                                isThisBoat = true;
                            }
                        }
                    }

                    if (isThisBoat && count == deckQuantity){
                        boat.setAlive(false);
                        for (int i = 1; i <= deckQuantity; i++) {
                            markFieldAsDestroyed(xUpperLeftCoordinate+1, y);
                        }
                        return true;
                    }

                    if (isThisBoat && count < deckQuantity){
                        return false;
                    }
                } else {
                    int xUpperLeftCoordinate = boat.getxUpperLeftCoordinate();
                    int yUpperLeftCoordinate = boat.getyUpperLeftCoordinate();
                    int count = 0;
                    boolean isThisBoat = false;

                    for (int i = yUpperLeftCoordinate; i < yUpperLeftCoordinate + deckQuantity; i++) {
                        if (fieldWithData[i][x-1] == 2){
                            count++;
                            if(fieldWithData[i][xUpperLeftCoordinate] == fieldWithData[y-1][x-1]){
                                isThisBoat = true;
                            }
                        }
                    }

                    if (isThisBoat && count == deckQuantity){
                        boat.setAlive(false);
                        for (int i = 1; i <= deckQuantity; i++) {
                            markFieldAsDestroyed(x, yUpperLeftCoordinate+i);
                        }
                        return true;
                    }

                    if (isThisBoat && count < deckQuantity){
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public void markFieldAsHit(int x, int y){
        this.fieldWithData[y-1][x-1] = 2;
        this.demonstratedField[y-1][x-1] = 2;
    }

    public void markFieldAsEmpty(int x, int y){
        this.fieldWithData[y-1][x-1] = 9;
        this.demonstratedField[y-1][x-1] = 9;
    }

    public void markFieldAsDestroyed(int x, int y) {
        this.fieldWithData[y-1][x-1] = 3;
        this.demonstratedField[y-1][x-1] = 3;
    }

    public void showCurrentStateOfFieldInConsole(){
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                System.out.print(this.fieldWithData[i][j] + " ");
            }
            System.out.println();
        }
    }
}
