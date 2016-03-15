package com.thd.jms.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

public class MessageSendAndReceive {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ActiveMQConnectionFactory("vm://localhost");

        Connection connection = factory.createConnection();
        connection.start();

        Queue queue = new ActiveMQQueue("testQueue");

        final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Message message = session.createTextMessage("Hello JMS!");

        MessageProducer producer = session.createProducer(queue);
        producer.send(message);

        System.out.println("Send Message Completed!");

        /*MessageConsumer consumer = session.createConsumer(queue);
        Message receiveMessage = consumer.receive();
        System.out.println(((TextMessage) receiveMessage).getText());*/
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message m) {
                TextMessage textMsg = (TextMessage) m;
                try {
                    System.out.println(textMsg.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
