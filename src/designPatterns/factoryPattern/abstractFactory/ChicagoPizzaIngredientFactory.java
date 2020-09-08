package designPatterns.factoryPattern.abstractFactory;

/**
 * concrete ingredient factory: create right ingredients for their region
 */
public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Sauce createSauce() {
        return new TomatoSauce();
    }

    @Override
    public Dough createDough() {
        return new ThickCrustDough();
    }
}
