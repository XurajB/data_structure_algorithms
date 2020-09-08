package designPatterns.factoryPattern;

public class SmsNotificationPicker extends NotificationPicker {
    @Override
    protected Notification getNotification() {
        return new SmsNotification();
    }
}
