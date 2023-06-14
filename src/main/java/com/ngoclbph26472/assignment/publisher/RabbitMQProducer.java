package com.ngoclbph26472.assignment.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j//Tạo 1 lịch trình ghi nhật ký
public class RabbitMQProducer {

    @Value("${rabbitmq.exchange.name}")//đọc giá trị từ file properties
    private String exchange;

    @Value("${rabbitmq.routing.key}")//đọc giá trị từ file properties
    private String routingKey;

//    C1:@Autowired
//    private RabbitTemplate rabbitTemplate;


    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        log.info(String.format("Message sent -> %s", message));
        rabbitTemplate.convertAndSend(exchange, routingKey, message);//chuyển đổi và gửi message đến exchange
        //và sử dụng routing key để định tuyến message đến queue
    }
}
