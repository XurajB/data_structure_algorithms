package designPatterns.builderPattern;

public class Test {
    public static void main(String[] args) {
        Email email = new Email.EmailBuilder()
                .setRecipient("hello@hello.com")
                .setMainText("Hi how are you")
                .setGreeting("Hi Hello,")
                .setClosing("Regards")
                .setTitle("Hi")
                .build();
        email.send();
    }
}
