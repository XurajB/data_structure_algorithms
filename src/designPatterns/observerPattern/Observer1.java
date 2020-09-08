package designPatterns.observerPattern;

public class Observer1 implements Observer {
    @Override
    public void update(String data) {
        System.out.println("observer 1 received - " + data);
    }
}
