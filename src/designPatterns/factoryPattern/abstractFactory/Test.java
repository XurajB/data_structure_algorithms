package designPatterns.factoryPattern.abstractFactory;

public class Test {
    public static void main(String[] args) {
        NYPizzaStore nyPizzaStore = new NYPizzaStore();
        Dough dough = nyPizzaStore.pizzaIngredientFactory.createDough();
        Sauce sauce = nyPizzaStore.pizzaIngredientFactory.createSauce();
    }
}
