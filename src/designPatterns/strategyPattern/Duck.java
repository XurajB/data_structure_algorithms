package designPatterns.strategyPattern;

/**
 * abstract duck
 * some duck can fly some can't (rubber).
 * we can create fly abstract method, but rubber duck would have empty implementation
 * we use strategy pattern and compose duck with behaviors
 */
public abstract class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public Duck() {
    }

    abstract void display();

    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void swim() {
        System.out.println("All ducks can float, even rubber");
    }
}
