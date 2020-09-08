package designPatterns.observerPattern;

import java.util.ArrayList;
import java.util.List;

// also called subject
// notifies all observers
public class EventSource {
    private final List<Observer> observers = new ArrayList<>();

    private void notifyObservers(String data) {
        observers.forEach(observer -> observer.update(data));
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // data got updated from other source
    public void updateData(String data) {
        notifyObservers(data);
    }
}
