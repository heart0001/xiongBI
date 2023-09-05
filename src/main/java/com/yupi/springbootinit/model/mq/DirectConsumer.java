package com.yupi.springbootinit.model.mq;

import com.rabbitmq.client.*;

public class DirectConsumer {

    private static final String EXCHANGE_NAME = "direct-exchange";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        // 创建队列，随机分配一个队列名称
        String queueName = "xiaoyu_queue";
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, EXCHANGE_NAME, "xiaoyu");

        // 创建队列，随机分配一个队列名称
        String queueName2 = "xiaopi_queue";
        channel.queueDeclare(queueName2, true, false, false, null);
        channel.queueBind(queueName2, EXCHANGE_NAME, "xiaopi");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback xiaoyuDeliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [xiaoyu] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };

        DeliverCallback xiaopiDeliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [xiaopi] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };

        channel.basicConsume(queueName, true, xiaoyuDeliverCallback, consumerTag -> {
        });
        channel.basicConsume(queueName2, true, xiaopiDeliverCallback, consumerTag -> {
        });
    }
}