package designPatterns.factoryPattern;

// factory method
abstract class NotificationPicker {
    // it its subclasses pick their own implementation
    protected abstract Notification getNotification();  // factory method
    // some generic use cases, common functionality shared by all classes
    public void sendNotification() {
        Notification notification = getNotification();
        notification.notifyUser();
    }
}
