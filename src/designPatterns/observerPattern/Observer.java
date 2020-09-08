package designPatterns.observerPattern;

/**
 * 1-to-many dependency between objects defined without making the objects tightly coupled
 * When there is a change, observers are updated automatically
 */
public interface Observer {
    void update(String data);
}
