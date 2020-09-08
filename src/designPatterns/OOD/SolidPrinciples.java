package designPatterns.OOD;

public class SolidPrinciples {
    // 5 principle of SOLID OO design
    /*
    (S)ingle Responsibility Principle: Each class and module in a program should focus on a single task. Example: A invoice class prints out invoice details and calculates sales tax and emails the invoice.
     Since there is a AND word, it is breaking single responsibility principle. We should create 3 classes out of this - Mailer, TaxCalculator, Invoice.
     By refactoring this, we can use individual feature like tax calculator independently.

    (O)pen/Closed Principle: It allows us to scale our code without having to deal with legacy components. Software elements (classes, modules, functions.. etc) should be open for extension
     but closed for modification. Well written class should not have to rewritten in order to add new feature. We can do this by using inheritance.

    (L)iskov Substitution Principle: Ability to replace any instance of a parent class with an instance of one of its child classes without negative side effects.
     Revolves around OO inheritance. Example: User, Admin extends from User. both can have settings one use array and another hash. if we are adding another functionality, we can't go through settings
     for both classes (parent, child) without an error. One solution can be to move settings to parent or another class

    (I)nterface Segregation Principle: Code should not be forced to depend on methods that it doesn't use. We should create separate interfaces

    (D)ependency Inversion Principle: High level objects should not depend on low level implementations. Both should depend on abstractions. Abstractions should not depend on details.
     Lower level components are designed to be consumed by higher level components. Presentation layer -> Business Layer -> DataAccess Layer. Lower layer should not depend on higher level. We can
     create an interface to create an abstractions between these layers. Adapter design pattern is an example.
     */
}
