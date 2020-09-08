package designPatterns.singleton;

// this class is thread safe
public class Singleton2 {
    private static Singleton2 instance;
    private Singleton2() {
        // private constructor
    }
    // one one thread can access this at one time
    // the issue is: we only need to check this the first time if instance is created, once it is created we don't need to synchronize
    // after the instance is created, synchronize is unneeded overhead
    public synchronized static Singleton2 getInstance() {
        // lazy initialization
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}
