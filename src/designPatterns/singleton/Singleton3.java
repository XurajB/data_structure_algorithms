package designPatterns.singleton;

// this class is thread safe
public class Singleton3 {
    private static Singleton3 instance = new Singleton3(); // created instance during class init, this only runs once (we rely on JVM to do this which guarantee only one instance)
    private Singleton3() {
        // private constructor
    }
    // read only
    public static Singleton3 getInstance() {
        return instance;
    }
}
