package main.java.task_02;

public class RubberDuck extends Duck{

    public RubberDuck(){
        quackBehavior = new Squeak();
        flyBehavior = new FlyWithLegs();
    }
}
