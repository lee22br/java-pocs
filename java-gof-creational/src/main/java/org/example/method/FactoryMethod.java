package org.example.method;

public class FactoryMethod {
}

/**
 * Message Interface
 */
interface Message {
    void send(String content);
}

/**
 * Concrete Message
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
    // The Factory Method
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
