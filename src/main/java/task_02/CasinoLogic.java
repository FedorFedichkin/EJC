package task_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CasinoLogic {
    private static int stockMoney = 500;
    private static int playerBet = 100;
    private static int playerWallet = 100;
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    static void launchCasino(){
        System.out.println("Welcome to Duck casino!");
        System.out.println("Your wallet: " + playerWallet + "$");
        System.out.println("Your bet: " + playerBet + "$");
        System.out.println("Casino stock: " + stockMoney + "$");
        while (playerWallet > 0 && stockMoney > 0){
            gameLoop();
        }

        System.out.println("Good luck next time!");
    }

    private static void gameLoop(){
        try{
            System.out.println("To continue the game enter \"y\" or enter \"n\" to quit. Then press \"Enter\".");
            String answer = bufferedReader.readLine();
            if (answer.toLowerCase().equals("y")){
                int chosenDuckNumber = makeBet();
                startFlying(chosenDuckNumber);
            } else if (answer.toLowerCase().equals("n")){
                closeReader();
                System.exit(0);
            } else gameLoop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int makeBet() {
        System.out.println("Please choose the duck number from 1 to 5.");
        int choice;
        try{
            choice = Integer.parseInt(bufferedReader.readLine());
            return choice;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static void startFlying(int chosenDuckNumber){
        List<Duck> ducks = new ArrayList<>();
        Random random = new Random();
        int competitionDistance = 100;

        for (int i=0; i<5; i++){
            ducks.add(random.nextBoolean() ? new MallardDuck() : new RubberDuck());
        }

        int[] ducksTimeScores = new int[5];
        for (int i=0; i<5; i++){
            ducksTimeScores[i] = ducks.get(i).performFly(competitionDistance);
        }

        int minimalCompetitionTime = ducksTimeScores[0];
        int indexOfDuckWithMinimalCompetitionTime = 0;
        for (int i=0; i<5; i++){
            if (ducksTimeScores[i] < minimalCompetitionTime){
                minimalCompetitionTime = ducksTimeScores[i];
                indexOfDuckWithMinimalCompetitionTime = i;
            }
        }

        if ((chosenDuckNumber-1) == indexOfDuckWithMinimalCompetitionTime){
            System.out.println("Your duck was first!" );
            System.out.println("You earned: " + playerBet + "$");
            stockMoney -= playerBet;
            playerWallet += playerBet;
            System.out.println("Your wallet: " + playerWallet + "$");
            System.out.println("Casino stock: " + stockMoney + "$");
        } else {
            System.out.println("Your duck lost...");
            playerWallet -= playerBet;
            stockMoney += playerBet;
            System.out.println("Your wallet: " + playerWallet + "$");
            System.out.println("Casino stock: " + stockMoney + "$");
        }
    }

    private static void closeReader(){
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
