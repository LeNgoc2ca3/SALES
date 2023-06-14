package com.ngoclbph26472.assignment.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQConsumer {

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})//lắng nghe tín hiệu, dùng để đọc message từ queue
    public void consume(String message){
        log.info(String.format("Received message -> %s", message));//Ghi lại message từ queue
    }
}
