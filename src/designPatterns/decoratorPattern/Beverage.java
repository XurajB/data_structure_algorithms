package designPatterns.decoratorPattern;

/**
 * Abstract component class
 */
public abstract class Beverage {
    String description;

    abstract double cost();
    abstract String getDescription();
}
