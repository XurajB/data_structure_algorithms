package designPatterns.factoryPattern.abstractFactory;

/**
 * Abstract factory provides an interface for creating family of products
 * Each ingredient can be different based on what type of pizza
 */
public interface PizzaIngredientFactory {
    Sauce createSauce();
    Dough createDough();
    // other ingredients like Cheese..
}
