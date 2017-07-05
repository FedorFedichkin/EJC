package main.java.task_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Initialisation {
    private static MainLogic mainLogic = new MainLogic();

    public static void battleInit(){
        mainLogic.generateEmptyField();
        putAllBoatsOnField();
        mainLogic.showCurrentStateOfFieldInConsole();
        showGreeting();
        launchGameLoop();
    }

    public static void showGreeting(){
        System.out.println();
        System.out.println("Welcome to the Sea Battle! You have 50 attempts to destroy 10 boats.");
        System.out.println("To hit/destroy a boat you should enter horizontal coordinate X (1 <= X <= 10), " +
                "press \"Enter\", then enter vertical coordinate Y (1 <= Y <= 10) and press \"Enter\" again.");
    }

    public static void launchGameLoop(){
        int x;
        int y;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            for (int i = 0; i < 50; i++) {
                System.out.println();
                System.out.print("X: ");
                x = Integer.parseInt(reader.readLine());
                System.out.print("Y: ");
                y = Integer.parseInt(reader.readLine());
                System.out.println();

                if (mainLogic.checkIfHit(x, y)){
                    mainLogic.markFieldAsHit(x, y);
                    mainLogic.checkIfDestroyed(x, y);
                } else {
                    mainLogic.markFieldAsEmpty(x, y);
                }
                mainLogic.showCurrentStateOfFieldInConsole();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void putAllBoatsOnField(){
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
