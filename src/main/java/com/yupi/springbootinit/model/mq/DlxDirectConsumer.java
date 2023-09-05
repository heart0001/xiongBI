package com.yupi.springbootinit.model.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.util.HashMap;
import java.util.Map;

public class DlxDirectConsumer {

    private static final String DEAD_EXCHANGE_NAME = "dlx-direct-exchange";

    private static final String WORK_EXCHANGE_NAME = "direct2-exchange";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(WORK_EXCHANGE_NAME, "direct");

        // 指定死信队列参数
        Map<String, Object> args = new HashMap<>();
        // 要绑定到哪个交换机
        args.put("x-dead-letter-exchange", DEAD_EXCHANGE_NAME);
        // 指定死信要转发到哪个死信队列
        args.put("x-dead-letter-routing-key", "waibao");

        // 创建队列，随机分配一个队列名称
        String queueName = "xiaodog_queue";
        channel.queueDeclare(queueName, true, false, false, args);
        channel.queueBind(queueName, WORK_EXCHANGE_NAME, "xiaodog");

        Map<String, Object> args2 = new HashMap<>();
        args2.put("x-dead-letter-exchange", DEAD_EXCHANGE_NAME);
        args2.put("x-dead-letter-routing-key", "laoban");

        // 创建队列，随机分配一个队列名称
        String queueName2 = "xiaocat_queue";
        channel.queueDeclare(queueName2, true, false, false, args2);
        channel.queueBind(queueName2, WORK_EXCHANGE_NAME, "xiaocat");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback xiaoyuDeliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            // 拒绝消息
            channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, false);
            System.out.println(" [xiaodog] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };

        DeliverCallback xiaopiDeliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            // 拒绝消息
            channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, false);
            System.out.println(" [xiaocat] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };

        channel.basicConsume(queueName, false, xiaoyuDeliverCallback, consumerTag -> {
        });
        channel.basicConsume(queueName2, false, xiaopiDeliverCallback, consumerTag -> {
        });
    }
}