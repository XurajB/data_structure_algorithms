package designPatterns.factoryPattern;

public class Test {
    public static void main(String[] args) {
        // factory
        NotificationFactory factory = new NotificationFactory();
        Notification notification = factory.createNotification("SMS");
        notification.notifyUser();

        // factory method
        SmsNotificationPicker sms = new SmsNotificationPicker();
        sms.sendNotification();
    }
}
