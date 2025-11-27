package org.example;

import jakarta.jms.*;
import javax.naming.InitialContext;

public class ConsumerJMS {

    public static void main(String[] args) {
        Connection connection = null;
        Session session = null;

        try {
            InitialContext context = new InitialContext();
            ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

            connection = factory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination queue = (Destination) context.lookup("queue/TEST.QUEUE");
            MessageConsumer consumer = session.createConsumer(queue);

            System.out.println("Waiting for messages on the queue...");

            Message message = consumer.receive(5000);

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("📩 Message received: " + text);
            } else if (message == null) {
                System.out.println("🚫 No message received after timeout.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                try { session.close(); } catch (JMSException e) { e.printStackTrace(); }
            }
            if (connection != null) {
                try { connection.close(); } catch (JMSException e) { e.printStackTrace(); }
            }
        }
    }
}
