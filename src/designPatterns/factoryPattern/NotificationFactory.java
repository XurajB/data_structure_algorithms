package designPatterns.factoryPattern;

/**
 * Factory class to instantiate the correct object based on provided parameter
 * In Factory pattern - we create objects without exposing the creation logic to the client and the client use the same common interface to create new type of object
 * Factory is fixed, it just have one implementation with no subclassing.
 * Use case: constructing the object is bit too complex to handle in the constructor
 *
 * Factory method: Defines an interface for creating objects, but let subclasses to decide which class to instantiate and refers to the newly created object through
 * common interface.
 * Use case: when we have some generic processing in a class, but want to vary which kind of object we actually use. #NotificationPicker
 *
 * Abstract factory: Offers the interface for creating a family of related objects, without explicitly specifying their classes
 * This is normally used for things like dependency injection/strategy, when we want to be able to create a whole family of objects that need to be of "same kind"
 * Example: Button with label for check out vs log out. Abstract factory produces all related classes for the use case (button + label + any other customization)
 *
 * Difference: between factory method and abstract factory: factory method is a single method, abstract factory is an object. Since factory method is a single method,
 * it can be overridden in a subclass so it uses inheritance and relies on a subclass to handle desired object instantiation. The abstract factory is an object that has multiple
 * factory methods in it. This class delegates the responsibility of object instantiation to another object via composition. Abstract factory creates a base class with abstract methods
 * defining methods for the objects that should be created.
 *
 * All three factory types do the same thing: they are a "smart constructor"
 */
public class NotificationFactory {
    // factory
    public Notification createNotification(String channel) {
        switch (channel) {
            case "SMS":
                return new SmsNotification();
            case "EMAIL":
                return new EmailNotification();
            case "PUSH":
                return new PushNotification();
            default:
                return null;
        }
    }
}
