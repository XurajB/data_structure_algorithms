package designPatterns.decoratorPattern;

public class HouseBlend extends Beverage {
    @Override
    double cost() {
        return 3;
    }

    @Override
    String getDescription() {
        return "House Blend";
    }
}
