package designPatterns.decoratorPattern;

public class Espresso extends Beverage {
    @Override
    double cost() {
        return 4;
    }

    @Override
    String getDescription() {
        return "Espresso";
    }
}
