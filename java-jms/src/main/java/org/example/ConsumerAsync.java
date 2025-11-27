package org.example;

import jakarta.jms.*;

import javax.naming.InitialContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsumerAsync {
    public static void main(String[] args) {
        Connection connection = null;
        Session session = null;

        try {
            InitialContext context = new InitialContext();
            ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

            connection = factory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination queue = (Destination) context.lookup("TEST.QUEUE");
            MessageConsumer consumer = session.createConsumer(queue);

            consumer.setMessageListener(new MyMsgListener());

            System.out.println("Waiting for messages on the ActiveMQ queue (Asynchronous)...");
            System.out.println("Press ENTER to shut down the consumer.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            reader.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Closes resources
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
