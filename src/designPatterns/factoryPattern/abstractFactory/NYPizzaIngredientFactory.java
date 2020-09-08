package designPatterns.factoryPattern.abstractFactory;

/**
 * concrete ingredient factory: create right ingredients for their region
 */
public class NYPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }
}
