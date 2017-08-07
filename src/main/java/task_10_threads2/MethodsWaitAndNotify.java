package task_10_threads2;

/**
 * Demonstration of use of methods Wait and Notify
 *
 * @author Fedor Fedichkin
 */
public class MethodsWaitAndNotify {
    public static void main(String[] args) {
        final Object key = new Object();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.err.println(i);
                    if (i == 3) {
                        synchronized (key) {
                            key.notifyAll();
                            System.err.println("released");
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.err.println("start");
        thread.start();
        synchronized (key) {
            System.err.println("waiting");
            try {
                key.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.err.println("finish");
    }
}

