package org.example;

import jakarta.jms.*;
import javax.naming.InitialContext;

public class ConsumerJMS {

    public static void main(String[] args) {
        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;

        try {
            InitialContext context = new InitialContext();
            ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

            connection = factory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination queue = (Destination) context.lookup("TEST.QUEUE");
            consumer = session.createConsumer(queue);

            System.out.println("Waiting for messages on the ActiveMQ queue (Loop)...");

            while (true) {
                Message message = consumer.receive(15000);

                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String text = textMessage.getText();
                    System.out.println("📩 Message received: " + text);
                } else if (message == null) {
                    // After 5 seconds, no message was present, so the loop continues
                    System.out.println("⏳ No messages for 5s. Still waiting...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // This finally block only executes if an exception interrupts the loop (or the program is manually terminated)
            if (consumer != null) {
                try { consumer.close(); } catch (JMSException e) { e.printStackTrace(); }
            }
            if (session != null) {
                try { session.close(); } catch (JMSException e) { e.printStackTrace(); }
            }
            if (connection != null) {
                try { connection.close(); } catch (JMSException e) { e.printStackTrace(); }
            }
            System.out.println("Consumer shut down.");
        }
    }
}
