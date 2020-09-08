package designPatterns.decoratorPattern;

public class Test {
    public static void main(String[] args) {

        // no condiments
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " " + beverage.cost());

        // 1 condiment
        Beverage beverage1 = new Espresso(); // espresso
        beverage1 = new Soy(beverage1); // wrap it with soy
        System.out.println(beverage1.getDescription() + " " + beverage1.cost());

        // 2 condiments
        Beverage beverage2 = new Espresso(); // espresso
        beverage2 = new Soy(beverage2); // wrap it with soy
        beverage2 = new Milk(beverage2); // wrap it with milk (milk(soy(bev)))
        System.out.println(beverage2.getDescription() + " " + beverage2.cost());
    }
}
