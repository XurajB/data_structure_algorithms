package designPatterns.singleton;

// thread safe, solves Singleton2 issue
public class Singleton4 {
    private static volatile Singleton4 instance; // every read will be from computer's main memory, not just from CPU cache. it ensures correct variable is read by all threads
    private Singleton4() {
        // private constructor
    }

    public static Singleton4 getInstance() {
        // lazy initialization
        if (instance == null) {
            synchronized (Singleton4.class) { // only enter here if instance is null
                if (instance == null) { // second check
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}
