package designPatterns.decoratorPattern;

/**
 * Another condiment
 */
public class Soy extends CondimentDecorator {

    private Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    String getDescription() {
        return beverage.getDescription() + ", Soy";
    }

    @Override
    double cost() {
        return beverage.cost() + 0.3;
    }
}
