package designPatterns.singleton;

/**
 * It restricts the instantiation of a class to one "single" instance. This is useful when exactly one instance is needed to coordinate
 * actions across the system.
 * In modern programming this is considered anti-pattern. Use DI to inject managed instances of classes.
 */

// this class is not thread safe
public class Singleton {
    private static Singleton instance;
    private Singleton() {
        // private constructor
    }
    // multiple treads can access this method at the same time and multiple instances could be created
    public static Singleton getInstance() {
        // lazy initialization
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}


