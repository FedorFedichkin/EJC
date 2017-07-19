package main.java.task_06_threads;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * OnlineShop. Waiting for a number from console. Number = quantity of demands to buy sausages.
 */
public class OnlineShop {
    private static int moneyInWallet = 500;
    private static int sausageQuantity = 100500;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.err.println("The quantity of sausages in stock: " + sausageQuantity);
            System.err.println("Please enter the quantity of sausages you wish to buy.");
            System.err.println("There is $" + moneyInWallet + " in your wallet.");
            buySomeSausages(Integer.parseInt(reader.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void buySomeSausages(int wantedSausageQuantity) {
        for (int i = 0; i < wantedSausageQuantity; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    buyOneSausage();
                }
            }).start();
        }
    }

    private static synchronized void buyOneSausage() {
        sausageQuantity -= 1;
        int sausagePrice = 100;
        moneyInWallet -= sausagePrice;
        if (sausageQuantity > 0) {
            if (moneyInWallet >= 0) {
                System.err.println("Sausage is bought. Sausages in stock: " + sausageQuantity);
                System.err.println("There is $" + moneyInWallet + " in your wallet.");
            } else {
                System.err.println("You have not enough money. Go find a job and come back later.");
            }
        } else {
            System.err.println("There is no more sausages. Please, come back later.");
        }
    }
}
