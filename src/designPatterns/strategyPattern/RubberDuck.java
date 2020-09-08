package designPatterns.strategyPattern;

public class RubberDuck extends Duck {
    public RubberDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Squeak();
    }
    @Override
    void display() {
        System.out.println("I am a rubber duck");
    }
}
