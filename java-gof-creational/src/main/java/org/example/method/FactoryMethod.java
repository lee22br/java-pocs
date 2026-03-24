package org.example.method;

public class FactoryMethod {
}

/**
 * Product Interface
 */
interface Message {
    void send(String content);
}

/**
 * Concrete Products
 */
class SmsMessage implements Message {
    @Override
    public void send(String content) {
        System.out.println("Sending SMS: " + content);
    }
}

class EmailMessage implements Message {
    @Override
    public void send(String content) {
        System.out.println("Sending Email: " + content);
    }
}

/**
 * Creator Class (The Factory)
 */
abstract class Notifier {
    // This is the Factory Method
    public abstract Message createMessage();

    public void processNotification(String text) {
        Message message = createMessage();
        message.send(text);
    }
}

/**
 * Concrete Creators
 */
class SmsNotifier extends Notifier {
    @Override
    public Message createMessage() {
        return new SmsMessage();
    }
}

class EmailNotifier extends Notifier {
    @Override
    public Message createMessage() {
        return new EmailMessage();
    }
}
