package com.yupi.springbootinit.model.mq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class TtlProducer {

    private final static String QUEUE_NAME = "ttl_queue";

    public static void main(String[] argv) throws Exception {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
//        factory.setUsername();
//        factory.setPassword();
//        factory.setPort();

        // 建立连接、创建频道
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            // 发送消息
            String message = "Hello World!";

            // 给消息指定过期时间
            AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                    .expiration("1000")
                    .build();
            channel.basicPublish("my-exchange", "routing-key", properties, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}