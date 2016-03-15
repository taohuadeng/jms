# 深入掌握JMS（四）：实战Queue
  　　Queue实现的是点到点模型，在下面的例子中，启动2个消费者共同监听一个Queue，然后循环给这个Queue中发送多个消息，我们依然采用ActiveMQ。

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

  　　运行这个例子会得到下面的输出结果：

	Consumer1 get Message:0
	Consumer2 get Message:1
	Consumer1 get Message:2
	Consumer2 get Message:3
	Consumer1 get Message:4
	Consumer2 get Message:5
	Consumer1 get Message:6
	Consumer2 get Message:7
	Consumer1 get Message:8
	Consumer2 get Message:9

  　　可以看出每个消息直被消费了一次，但是如果有多个消费者同时监听一个Queue的话，无法确定一个消息最终会被哪一个消费者消费。

