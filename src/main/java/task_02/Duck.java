package main.java.task_02;

public abstract class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    Duck(){
    }

    int performFly(int competitionDistance){
        return flyBehavior.fly(competitionDistance);
    }

    public void performQuack(){
        quackBehavior.quack();
    }
}
