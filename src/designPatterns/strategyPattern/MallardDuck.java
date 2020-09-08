package designPatterns.strategyPattern;

// kind of a real duck
public class MallardDuck extends Duck {
    public MallardDuck() {
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }

    @Override
    void display() {
        System.out.println("I am real Mallard duck");
    }
}
