package org.example;

import jakarta.jms.*;

import javax.naming.InitialContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;

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

            Destination queue = (Destination) context.lookup("TEST.QUEUE");
            MessageProducer producer = session.createProducer(queue);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String inputLine = "";
            int messageCount = 0;

            System.out.println("--- Active JMS Producer (Editor) ---");
            System.out.println("Type the message you wish to send and press ENTER.");
            System.out.println("Type 'exit' to quit.");
            System.out.println("------------------------------------");

            while (true) {
                System.out.print("\nEnter your message: ");
                inputLine = reader.readLine();
                if (null == inputLine  || inputLine.trim().equalsIgnoreCase("exit")) {
                    break;
                }
                if (inputLine.trim().isEmpty()) {
                    continue;
                }
                messageCount++;
                TextMessage message = session.createTextMessage(inputLine.trim());
                producer.send(message);
                System.out.println("✅ Sent (" + messageCount + "): \"" + inputLine.trim() + "\"");
            }
            System.out.println("\nProducer shut down. Total messages sent: " + messageCount);
        } catch (Exception e) {
            System.err.println("An error occurred in the producer.");
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