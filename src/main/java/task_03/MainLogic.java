package main.java.task_03;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
* This class is made for back-end calculations and interaction.
* */
public class MainLogic {
    private final static int FIELD_SIZE = 10;
    private char[][] fieldWithData = new char[FIELD_SIZE][FIELD_SIZE];
    private char[][] demonstratedField = new char[FIELD_SIZE][FIELD_SIZE];
    private List<Boat> boats = new ArrayList<>();

    /**
     * Generating two empty fields: fieldWithData is for storage of boats positions, demonstratedField is for showing to player.
     */
    void generateEmptyFields() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                this.fieldWithData[i][j] = 0x20DE;
                this.demonstratedField[i][j] = 0x20DE;
            }
        }
    }

    /**
     * @return List of boats placed on the field.
     */
    List<Boat> getBoats() {
        return this.boats;
    }

    /**
     * Generates the position and orientation of boat randomly and saves these parameters on the fieldWithData.
     *
     * @param deckQuantity - defines the number of field cells corresponding to a boat size.
     */
    void putBoatOnField(int deckQuantity) {
        int maxNumberOfBoatDecks = FIELD_SIZE - 2;

        if (deckQuantity > maxNumberOfBoatDecks) {
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
            if (isBoatHorizontal) {
                xUpperLeft = random.nextInt(FIELD_SIZE - deckQuantity - 1) + 1;
                yUpperLeft = random.nextInt(maxNumberOfBoatDecks) + 1;
            } else {
                xUpperLeft = random.nextInt(maxNumberOfBoatDecks) + 1;
                yUpperLeft = random.nextInt(FIELD_SIZE - deckQuantity - 1) + 1;
            }

        } while (!isPossibleToPutBoatOnField(xUpperLeft, yUpperLeft, deckQuantity, isBoatHorizontal));

        boats.add(new Boat(deckQuantity, xUpperLeft, yUpperLeft, isBoatHorizontal, true));

        if (isBoatHorizontal) {
            for (int i = xUpperLeft; i < xUpperLeft + deckQuantity; i++) {
                this.fieldWithData[yUpperLeft][i] = 'b';
            }
        } else {
            for (int i = yUpperLeft; i < yUpperLeft + deckQuantity; i++) {
                this.fieldWithData[i][xUpperLeft] = 'b';
            }
        }
    }

    /**
     * Checks if it is possible to put a boat of a defined orientation and of a defined size on a defined position.
     *
     * @param xUpperLeftCoordinate - X coordinate of desired boat position.
     * @param yUpperLeftCoordinate - Y coordinate of desired boat position.
     * @param deckQuantity         - boat size (number of its cells on a field).
     * @param isBoatHorizontal     - desired orientation of boat (true if horizontal, otherwise is false).
     * @return true if it is possible to put boat with desired parameters on a field (fieldWithData here).
     */
    private boolean isPossibleToPutBoatOnField(int xUpperLeftCoordinate, int yUpperLeftCoordinate,
                                               int deckQuantity, boolean isBoatHorizontal) {
        if (isBoatHorizontal) {
            if ((xUpperLeftCoordinate + deckQuantity) > FIELD_SIZE) {
                return false;
            } else {
                for (int i = 0; i < deckQuantity; i++) {
                    for (int j = xUpperLeftCoordinate - 1; j < xUpperLeftCoordinate + 2; j++) {
                        for (int k = yUpperLeftCoordinate - 1; k < yUpperLeftCoordinate + 2; k++) {
                            if (k < FIELD_SIZE && (j + i) < FIELD_SIZE && this.fieldWithData[k][j + i] != 0x20DE)
                                return false;
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
                            if (j < FIELD_SIZE && (k + i) < FIELD_SIZE && this.fieldWithData[k + i][j] != 0x20DE)
                                return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    boolean checkIfBoat(int x, int y) {
        return this.fieldWithData[y - 1][x - 1] == 'b' || this.fieldWithData[y - 1][x - 1] == 0x2757;
    }

    boolean checkIfDestroyed(int x, int y) {
        int deckQuantity;
        boolean isBoatHorizontal;
        for (Boat boat : boats) {
            if (boat.isAlive()) {
                isBoatHorizontal = boat.isBoatHorizontal();
                deckQuantity = boat.getDeckQuantity();
                if (isBoatHorizontal) {
                    int xUpperLeftCoordinate = boat.getxUpperLeftCoordinate();
                    int yUpperLeftCoordinate = boat.getyUpperLeftCoordinate();
                    int count = 0;
                    boolean isThisBoat = false;

                    for (int i = xUpperLeftCoordinate; i < xUpperLeftCoordinate + deckQuantity; i++) {
                        if (fieldWithData[y - 1][i] == 0x2757) {
                            count++;
                            if (fieldWithData[yUpperLeftCoordinate][i] == fieldWithData[y - 1][x - 1]) {
                                isThisBoat = true;
                            }
                        }
                    }

                    if (isThisBoat && count == deckQuantity) {
                        boat.setAlive(false);
                        for (int i = 1; i <= deckQuantity; i++) {
                            markFieldAsDestroyedBoat(xUpperLeftCoordinate + i, y);
                        }
                        return true;
                    }

                    if (isThisBoat && count < deckQuantity) {
                        return false;
                    }
                } else {
                    int xUpperLeftCoordinate = boat.getxUpperLeftCoordinate();
                    int yUpperLeftCoordinate = boat.getyUpperLeftCoordinate();
                    int count = 0;
                    boolean isThisBoat = false;

                    for (int i = yUpperLeftCoordinate; i < yUpperLeftCoordinate + deckQuantity; i++) {
                        if (fieldWithData[i][x - 1] == 0x2757) {
                            count++;
                            if (fieldWithData[i][xUpperLeftCoordinate] == fieldWithData[y - 1][x - 1]) {
                                isThisBoat = true;
                            }
                        }
                    }

                    if (isThisBoat && count == deckQuantity) {
                        boat.setAlive(false);
                        for (int i = 1; i <= deckQuantity; i++) {
                            markFieldAsDestroyedBoat(x, yUpperLeftCoordinate + i);
                        }
                        return true;
                    }

                    if (isThisBoat && count < deckQuantity) {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    void markFieldAsHit(int x, int y) {
        this.fieldWithData[y - 1][x - 1] = 0x2757;
        this.demonstratedField[y - 1][x - 1] = 0x2757;
    }

    void markFieldAsEmpty(int x, int y) {
        if (this.fieldWithData[y - 1][x - 1] != 0x2757 && this.demonstratedField[y - 1][x - 1] != 0x2757
                && this.fieldWithData[y - 1][x - 1] != 0x2620 && this.demonstratedField[y - 1][x - 1] != 0x2620) {
            this.fieldWithData[y - 1][x - 1] = 0x2715;
            this.demonstratedField[y - 1][x - 1] = 0x2715;
        }
    }

    private void markFieldAsDestroyedBoat(int x, int y) {
        this.fieldWithData[y - 1][x - 1] = 0x2620;
        this.demonstratedField[y - 1][x - 1] = 0x2620;
    }

    void showCurrentStateOfFieldInConsole() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                switch (this.demonstratedField[i][j]) {
                    case 0x20DE:
                        System.out.print(" " + this.demonstratedField[i][j] + " ");
                        break;
                    case 0x2757:
                        System.out.print("\u2008" + this.demonstratedField[i][j] + "\u2008");
                        break;
                    case 0x2715:
                        System.out.print("\u200A" + this.demonstratedField[i][j] + "\u2009");
                        break;
                    case 0x2620:
                        System.out.print("\u200A" + this.demonstratedField[i][j] + "\u200A");
                        break;
                    default:
                        System.out.print(this.demonstratedField[i][j]);
                        break;
                }
            }
            System.out.println();
        }
    }
}
