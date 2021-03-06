# 深入掌握JMS（七）：DeliveryMode例子
   　　在下面的例子中，分别发送一个Persistent和nonpersistent的消息，然后关闭退出JMS。

	package com.thd.jms.jms;

	import javax.jms.Connection;
	import javax.jms.DeliveryMode;
	import javax.jms.MessageProducer;
	import javax.jms.Queue;
	import javax.jms.Session;
	
	import org.apache.activemq.ActiveMQConnectionFactory;
	import org.apache.activemq.command.ActiveMQQueue;
	
	public class DeliveryModeSendTest {
	    public static void main(String[] args) throws Exception {
	        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("vm://localhost");
	
	        Connection connection = factory.createConnection();
	        connection.start();
	
	        Queue queue = new ActiveMQQueue("testQueue");
	        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	
	        MessageProducer producer = session.createProducer(queue);
	        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
	        producer.send(session.createTextMessage("A persistent Message"));
	
	        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	        producer.send(session.createTextMessage("A non persistent Message"));
	
	        System.out.println("Send messages successfully!");
	    }
	}


   　　运行上面的程序，当输出“Send messages sucessfully!”时，说明两个消息都已经发送成功，然后我们结束它，来停止JMS Provider。

   　　接下来我们重新启动JMS Provicer，然后添加一个消费者：

	package com.thd.jms.jms;

	import javax.jms.Connection;
	import javax.jms.DeliveryMode;
	import javax.jms.MessageProducer;
	import javax.jms.Queue;
	import javax.jms.Session;
	
	import org.apache.activemq.ActiveMQConnectionFactory;
	import org.apache.activemq.command.ActiveMQQueue;
	
	public class DeliveryModeSendTest {
	    public static void main(String[] args) throws Exception {
	        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("vm://localhost");
	
	        Connection connection = factory.createConnection();
	        connection.start();
	
	        Queue queue = new ActiveMQQueue("testQueue");
	        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	
	        MessageProducer producer = session.createProducer(queue);
	        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
	        producer.send(session.createTextMessage("A persistent Message"));
	
	        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	        producer.send(session.createTextMessage("A non persistent Message"));
	
	        System.out.println("Send messages successfully!");
	    }
	}

   　　运行上面的程序，可以得到下面的输出结果：

	Consumer get A persistent Message

   　　可以看出消息消费者只接收到一个消息，它是一个Persistent的消息。而刚才发送的non persistent消息已经丢失了。

   　　另外, 如果发送一个non persistent消息, 而刚好这个时候没有消费者在监听, 这个消息也会丢失。