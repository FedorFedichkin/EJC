package main.java.task_02;

import java.util.Random;

public class FlyWithWings implements FlyBehavior{

    private final static int MIN_SPEED = 10;
    private final static int MAX_SPEED = 100;
    private final static int TIME_INTERVAL = 1;

    @Override
    public int fly(int totalDistance) {
        int speed;
        int currentDistance = 0;

        for (int i=1; i<10; i++){
            speed = getSpeed();
            currentDistance += speed * TIME_INTERVAL;
            if (currentDistance >= totalDistance){
                return TIME_INTERVAL * i;
            }
        }
        return -1;
    }

    private int getSpeed(){
        Random random = new Random();
        int speed = random.nextInt(MAX_SPEED-MIN_SPEED) + MIN_SPEED;
        speed = speed >> 1;
        return speed;
    }
}
