package designPatterns.strategyPattern;

public class Test {
    public static void main(String[] args) {
        // rubber duck
        Duck duck1 = new RubberDuck();
        duck1.display();
        duck1.performFly();
        duck1.performQuack();
        duck1.swim();

        // real duck
        Duck duck2 = new MallardDuck();
        duck2.display();
        duck2.performFly();
        duck2.performQuack();
    }
}
