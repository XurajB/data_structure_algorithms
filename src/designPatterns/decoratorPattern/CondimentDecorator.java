package designPatterns.decoratorPattern;

/**
 * abstract Decorator, also extends Beverage (the object it is going to decorate)
 * this class needs to be inter-changeable with Beverage
 */
public abstract class CondimentDecorator extends Beverage {
    abstract String getDescription();
}
