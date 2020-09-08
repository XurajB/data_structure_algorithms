package designPatterns.strategyPattern;

// rubber duck can only squeak
public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Squeak");
    }
}
