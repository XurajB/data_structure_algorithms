package designPatterns.builderPattern;

/**
 * Provides a flexible solution to various object creation problems. The intent is to separate the construction of a complex object from its representation.
 * It lets clients create different representations of a complex object. It encapsulates creating and assembling the parts of a complex object in a separate Builder object.
 */
public class Email {
    private final String title;
    private final String recipient;
    private final String message;
    public Email(String title, String recipient, String message) {
        this.title = title;
        this.recipient = recipient;
        this.message = message;
    }
    public void send() {
        System.out.println("Email sent: to: " + recipient + " title: " + title + " message: " + message);
    }

    public static class EmailBuilder {
        private String recipient;
        private String title;
        private String greeting;
        private String mainText;
        private String closing;
        public EmailBuilder setRecipient(String recipient) {
            this.recipient = recipient;
            return this;
        }
        public EmailBuilder setTitle(String title) {
            this.title = title;
            return this;
        }
        public EmailBuilder setGreeting(String greeting) {
            this.greeting = greeting;
            return this;
        }
        public EmailBuilder setMainText(String mainText) {
            this.mainText = mainText;
            return this;
        }
        public EmailBuilder setClosing(String closing) {
            this.closing = closing;
            return this;
        }
        public Email build() {
            String message = greeting+"\n"+mainText+"\n"+closing;
            return new Email(title, recipient, message);
        }
    }
}
