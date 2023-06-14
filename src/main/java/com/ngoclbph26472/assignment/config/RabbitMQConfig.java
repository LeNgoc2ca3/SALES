package com.ngoclbph26472.assignment.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")//đọc giá trị từ file properties
    private String queue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    //    spring bean for rabbitmq queue
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    //    spring bean for rabbitmq exchange
    @Bean
    public TopicExchange exchange() {//trao đổi
        return new TopicExchange(exchange);
    }



    //bingding between queue and exchange
    @Bean
    public Binding binding() {//liên kết 2 phương  thức trên bằng cách sử dung routing key
        return BindingBuilder.bind(queue()).to(exchange())
                .with(routingKey);
    }


//    ConnectionFactory
//    RabbitTemplate
//    RabbitAdmin
//    Spring boot sẽ tự động cấu hình 3 cái trên
}
