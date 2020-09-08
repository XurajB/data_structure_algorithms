package designPatterns.strategyPattern;
// flying behavior for ducks that can fly
public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying with my wings");
    }
}
