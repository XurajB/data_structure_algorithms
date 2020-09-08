package designPatterns.decoratorPattern;

/**
 * concrete condiment with its own description and price
 * has Beverage so we can wrap it
 */
public class Milk extends CondimentDecorator {

    private Beverage beverage;

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    String getDescription() {
        return beverage.getDescription() + ", Milk";
    }

    @Override
    double cost() {
        return beverage.cost() + 0.2;
    }
}
