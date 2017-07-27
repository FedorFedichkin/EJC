package task_06_threads;

import java.io.IOException;

/**
 * Rabbit eats a carrot every second. Please press the "Enter" button to stop it.
 */
public class Rabbit {
    private volatile static boolean flag = true;
    private static volatile int carrot = 100500;

    public static void main(String[] args) {
        new Rabbit.ThreadFlagSetter().start();
        new Rabbit.ThreadFlagReader().start();
    }

    private static class ThreadFlagSetter extends Thread {
        @Override
        public void run() {
            System.err.println("Carrots in stock: " + carrot);
            System.err.println("To stop eating, please press the \"Enter\" button.");
            System.err.println();
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            flag = false;
        }
    }

    private static class ThreadFlagReader extends Thread {
        @Override
        public void run() {
            while (flag) {
                carrot -= 1;
                System.err.println("Carrot is eaten. Carrots in stock: " + carrot + ".");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
