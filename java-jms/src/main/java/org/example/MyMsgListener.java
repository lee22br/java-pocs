package org.example;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

public class MyMsgListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("🔔 Asynchronous message received: " + text);
            }
        } catch (JMSException e) {
            System.err.println("Error processing message: " + e.getMessage());
        }
    }
}
