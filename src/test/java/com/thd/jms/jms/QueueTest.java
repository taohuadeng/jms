package com.thd.jms.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

public class QueueTest {
    public static void main(String[] args) throws Exception {
        //启动ActiveMQ
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("vm://localhost");
        Connection connection = factory.createConnection();
        connection.start();

        //创建一个Queue
        Queue queue = new ActiveMQQueue("testQueue");
        //创建一个Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //注册消费者1
        MessageConsumer consumer1 = session.createConsumer(queue);
        consumer1.setMessageListener(new MessageListener() {
            public void onMessage(Message m) {
                try {
                    System.out.println("Consumer1 get " + ((TextMessage) m).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        //注册消费者2
        MessageConsumer consumer2 = session.createConsumer(queue);
        consumer2.setMessageListener(new MessageListener() {
            public void onMessage(Message m) {
                try {
                    System.out.println("Consumer2 get " + ((TextMessage) m).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        //创建一个生产者，然后发送多个消息。
        MessageProducer producer = session.createProducer(queue);
        for (int i = 0; i < 10; i++) {
            producer.send(session.createTextMessage("Message:" + i));
        }
    }
}
