package com.thd.jm.jms;

import com.tbc.soa.core.impl.JsonConverter;
import com.thd.jm.domain.Job;

import javax.jms.*;

public class JobMessageProducer {
    public ConnectionFactory connectionFactory;
    public static JsonConverter jsonConverter = new JsonConverter();
    /**
     * 消息发送目的地名称
     */
    public String destinationName;

    public void jobMessageSend(Job job) throws JMSException {
        // 获取连接
        Connection connection = connectionFactory.createConnection();
        // 开启连接
        connection.start();
        // 设置会话
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        // 设置队列名称
        Destination destination = session.createQueue(destinationName);

        // 设置发送者
        MessageProducer messageProducer = session.createProducer(destination);
        Message message = session.createTextMessage(jsonConverter.toText(job));
        message.setStringProperty("appCode", "ems");
        messageProducer.send(message);
        // 提交
        session.commit();

        // 关闭
        messageProducer.close();
        session.close();
        connection.close();
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }
}