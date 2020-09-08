package designPatterns.observerPattern;

public class Test {
    public static void main(String[] args) {
        EventSource source = new EventSource();

        Observer1 observer1 = new Observer1();
        Observer2 observer2 = new Observer2();

        source.addObserver(observer1);
        source.updateData("data 1");

        source.addObserver(observer2);
        source.updateData("data 2");
    }
}
