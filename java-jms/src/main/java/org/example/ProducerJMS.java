package org.example;

import jakarta.jms.*;

import javax.naming.InitialContext;

public class ProducerJMS {

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
            MessageProducer producer = session.createProducer(queue);

            TextMessage message = session.createTextMessage("Hello, JMS world! This is a test message.");
            producer.send(message);

            System.out.println("✅ Message sent: " + message.getText());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}