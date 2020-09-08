package designPatterns.strategyPattern;
// flying behavior for ducks that can't fly
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I can't fly");
    }
}
