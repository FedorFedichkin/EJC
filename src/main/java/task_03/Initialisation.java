package main.java.task_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * This class is for game initialization and further use of MainLogic class.
 */
public class Initialisation {
    private static MainLogic mainLogic = new MainLogic();

    /**
     * Initialization happens here.
     */
    static void battleInit() {
        mainLogic.generateEmptyFields();
        putAllBoatsOnField();
        mainLogic.showCurrentStateOfFieldInConsole();
        showGreeting();
        launchGameLoop();
    }

    /**
     * Showing greeting message, game rules and a legend.
     */
    private static void showGreeting() {
        System.out.println();
        System.out.println("Welcome to the Sea Battle! You have 50 attempts to destroy 10 boats.");
        System.out.println("To shoot you should enter horizontal coordinate X (1 <= X <= 10), " +
                "press \"Enter\", then enter vertical coordinate Y (1 <= Y <= 10) and press \"Enter\" again.");
        System.out.println(" " + "\u20DE" + "  - is a cell of a field");
        System.out.println("\u200A" + "\u2715" + "\u2009" + " - an empty field");
        System.out.println("\u2008" + "\u2757" + "\u2008" + " - a hit boat");
        System.out.println("\u200A" + "\u2620" + "\u200A" + " - destroyed boat");
    }

    /**
     * This method is launching loop where interaction with player happens.
     */
    private static void launchGameLoop() {
        int x;
        int y;
        List<Boat> boats = mainLogic.getBoats();
        int boatsQuantity = boats.size();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int loopCount = 10;
            int i = 0;
            do {
                i++;
                if (i == loopCount + 1) {
                    System.out.println("You failed this game. Try again!");
                    break;
                }
                System.out.println();
                System.out.println("This is your " + i + " attempt.");
                System.out.print("X: ");
                x = Integer.parseInt(reader.readLine());
                if (x > 10 || x < 1) {
                    i--;
                    System.out.println("The value you entered is incorrect. Try again.");
                    continue;
                }
                System.out.print("Y: ");
                y = Integer.parseInt(reader.readLine());
                System.out.println();
                if (y > 10 || y < 1) {
                    i--;
                    System.out.println("The value you entered is incorrect. Try again.");
                    continue;
                }
                if (mainLogic.checkIfBoat(x, y)) {
                    mainLogic.markFieldAsHit(x, y);
                    mainLogic.checkIfDestroyed(x, y);
                } else {
                    mainLogic.markFieldAsEmpty(x, y);
                }
                mainLogic.showCurrentStateOfFieldInConsole();
                int counter = 0;
                for (Boat boat : boats) {
                    if (!boat.isAlive()) {
                        counter++;
                    }
                }
                if (counter == boatsQuantity) {
                    System.out.println("It is victory! Congratulations!");
                    System.out.println("Number of your attempts is: " + i);
                    break;
                }
            } while (i < loopCount + 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Choose boats of different size (from 1 to 4 cells) to put on the field.
     */
    private static void putAllBoatsOnField() {
        mainLogic.putBoatOnField(4);

        mainLogic.putBoatOnField(3);
        mainLogic.putBoatOnField(3);

        mainLogic.putBoatOnField(2);
        mainLogic.putBoatOnField(2);
        mainLogic.putBoatOnField(2);

        mainLogic.putBoatOnField(1);
        mainLogic.putBoatOnField(1);
        mainLogic.putBoatOnField(1);
        mainLogic.putBoatOnField(1);
    }
}
