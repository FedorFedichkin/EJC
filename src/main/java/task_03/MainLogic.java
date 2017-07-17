package main.java.task_03;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
* This class is made for back-end calculations, interaction and visualisation.
* */
public class MainLogic {
    private final static int FIELD_SIZE = 10;
    private static final int DESTROYED_BOAT_FIELD = 0x2620;
    private static final int INITIAL_FIELD = 0x20DE;
    private static final int HIT_BOAT_FIELD = 0x2757;
    private static final int EMPTY_FIELD = 0x2715;
    private static final char BOAT_FIELD = 'b';
    private char[][] fieldWithData = new char[FIELD_SIZE][FIELD_SIZE];
    private char[][] demonstratedField = new char[FIELD_SIZE][FIELD_SIZE];
    private List<Boat> boats = new ArrayList<>();

    /**
     * Generating two empty fields: fieldWithData is for storage of positions of boats, demonstratedField is for showing to player.
     */
    void generateEmptyFields() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                this.fieldWithData[i][j] = INITIAL_FIELD;
                this.demonstratedField[i][j] = INITIAL_FIELD;
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
        int upperLeftX;
        int upperLeftY;

        do {
            isBoatHorizontal = random.nextBoolean();
            if (isBoatHorizontal) {
                upperLeftX = random.nextInt(FIELD_SIZE - deckQuantity - 1) + 1;
                upperLeftY = random.nextInt(maxNumberOfBoatDecks) + 1;
            } else {
                upperLeftX = random.nextInt(maxNumberOfBoatDecks) + 1;
                upperLeftY = random.nextInt(FIELD_SIZE - deckQuantity - 1) + 1;
            }
        } while (!isPossibleToPutBoatOnField(upperLeftX, upperLeftY, deckQuantity, isBoatHorizontal));

        boats.add(new Boat(deckQuantity, upperLeftX, upperLeftY, isBoatHorizontal, true));

        if (isBoatHorizontal) {
            for (int i = upperLeftX; i < upperLeftX + deckQuantity; i++) {
                this.fieldWithData[upperLeftY][i] = BOAT_FIELD;
            }
        } else {
            for (int i = upperLeftY; i < upperLeftY + deckQuantity; i++) {
                this.fieldWithData[i][upperLeftX] = BOAT_FIELD;
            }
        }
    }

    /**
     * Checks if it is possible to put a boat of a defined orientation and of a defined size on a defined position.
     *
     * @param upperLeftCoordinateX - X coordinate of desired boat position.
     * @param upperLeftCoordinateY - Y coordinate of desired boat position.
     * @param deckQuantity         - boat size (number of its cells on a field).
     * @param isBoatHorizontal     - desired orientation of boat (true if horizontal, otherwise is false).
     * @return true if it is possible to put boat with desired parameters on a field (fieldWithData here).
     */
    private boolean isPossibleToPutBoatOnField(int upperLeftCoordinateX, int upperLeftCoordinateY,
                                               int deckQuantity, boolean isBoatHorizontal) {
        if (isBoatHorizontal) {
            if ((upperLeftCoordinateX + deckQuantity) > FIELD_SIZE) {
                return false;
            } else {
                for (int i = 0; i < deckQuantity; i++) {
                    for (int j = upperLeftCoordinateX - 1; j < upperLeftCoordinateX + 2; j++) {
                        for (int k = upperLeftCoordinateY - 1; k < upperLeftCoordinateY + 2; k++) {
                            if (k < FIELD_SIZE && (j + i) < FIELD_SIZE &&
                                    this.fieldWithData[k][j + i] != INITIAL_FIELD) {
                                return false;
                            }
                        }
                    }
                }
            }
        } else {
            if ((upperLeftCoordinateY + deckQuantity) > FIELD_SIZE) {
                return false;
            } else {
                for (int i = 0; i < deckQuantity; i++) {
                    for (int j = upperLeftCoordinateX - 1; j < upperLeftCoordinateX + 2; j++) {
                        for (int k = upperLeftCoordinateY - 1; k < upperLeftCoordinateY + 2; k++) {
                            if (j < FIELD_SIZE && (k + i) < FIELD_SIZE &&
                                    this.fieldWithData[k + i][j] != INITIAL_FIELD) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    boolean checkIfBoat(int column, int row) {
        return this.fieldWithData[row - 1][column - 1] == BOAT_FIELD ||
                this.fieldWithData[row - 1][column - 1] == HIT_BOAT_FIELD;
    }

    boolean checkIfDestroyed(int column, int row) {
        int deckQuantity;
        boolean isBoatHorizontal;
        for (Boat boat : boats) {
            if (boat.isAlive()) {
                isBoatHorizontal = boat.isBoatHorizontal();
                deckQuantity = boat.getDeckQuantity();
                if (isBoatHorizontal) {
                    int upperLeftCoordinateX = boat.getUpperLeftCoordinateX();
                    int upperLeftCoordinateY = boat.getUpperLeftCoordinateY();
                    int count = 0;
                    boolean isThisBoat = false;

                    for (int i = upperLeftCoordinateX; i < upperLeftCoordinateX + deckQuantity; i++) {
                        if (fieldWithData[row - 1][i] == HIT_BOAT_FIELD) {
                            count++;
                            if (fieldWithData[upperLeftCoordinateY][i] == fieldWithData[row - 1][column - 1]) {
                                isThisBoat = true;
                            }
                        }
                    }
                    if (isThisBoat && count == deckQuantity) {
                        boat.setAlive(false);
                        for (int i = 1; i <= deckQuantity; i++) {
                            markFieldAsDestroyedBoat(upperLeftCoordinateX + i, row);
                        }
                        return true;
                    }
                    if (isThisBoat && count < deckQuantity) {
                        return false;
                    }
                } else {
                    int upperLeftCoordinateX = boat.getUpperLeftCoordinateX();
                    int upperLeftCoordinateY = boat.getUpperLeftCoordinateY();
                    int count = 0;
                    boolean isThisBoat = false;

                    for (int i = upperLeftCoordinateY; i < upperLeftCoordinateY + deckQuantity; i++) {
                        if (fieldWithData[i][column - 1] == HIT_BOAT_FIELD) {
                            count++;
                            if (fieldWithData[i][upperLeftCoordinateX] == fieldWithData[row - 1][column - 1]) {
                                isThisBoat = true;
                            }
                        }
                    }
                    if (isThisBoat && count == deckQuantity) {
                        boat.setAlive(false);
                        for (int i = 1; i <= deckQuantity; i++) {
                            markFieldAsDestroyedBoat(column, upperLeftCoordinateY + i);
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

    void markFieldAsHit(int column, int row) {
        this.fieldWithData[row - 1][column - 1] = HIT_BOAT_FIELD;
        this.demonstratedField[row - 1][column - 1] = HIT_BOAT_FIELD;
    }

    void markFieldAsEmpty(int column, int row) {
        if (this.fieldWithData[row - 1][column - 1] != HIT_BOAT_FIELD &&
                this.demonstratedField[row - 1][column - 1] != HIT_BOAT_FIELD &&
                this.fieldWithData[row - 1][column - 1] != DESTROYED_BOAT_FIELD &&
                this.demonstratedField[row - 1][column - 1] != DESTROYED_BOAT_FIELD) {
            this.fieldWithData[row - 1][column - 1] = EMPTY_FIELD;
            this.demonstratedField[row - 1][column - 1] = EMPTY_FIELD;
        }
    }

    private void markFieldAsDestroyedBoat(int column, int row) {
        this.fieldWithData[row - 1][column - 1] = DESTROYED_BOAT_FIELD;
        this.demonstratedField[row - 1][column - 1] = DESTROYED_BOAT_FIELD;
    }

    void showCurrentStateOfFieldInConsole() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                switch (this.demonstratedField[i][j]) {
                    case INITIAL_FIELD:
                        System.out.print(" " + this.demonstratedField[i][j] + " ");
                        break;
                    case HIT_BOAT_FIELD:
                        System.out.print("\u2008" + this.demonstratedField[i][j] + "\u2008");
                        break;
                    case EMPTY_FIELD:
                        System.out.print("\u200A" + this.demonstratedField[i][j] + "\u2009");
                        break;
                    case DESTROYED_BOAT_FIELD:
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
