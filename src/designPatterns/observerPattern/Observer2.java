package designPatterns.observerPattern;

public class Observer2 implements Observer {
    @Override
    public void update(String data) {
        System.out.println("observer 2 received - " + data);
    }
}
