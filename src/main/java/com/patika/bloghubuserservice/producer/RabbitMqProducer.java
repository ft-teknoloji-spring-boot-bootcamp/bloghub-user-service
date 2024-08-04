package com.patika.bloghubuserservice.producer;

import com.patika.bloghubuserservice.config.RabbitMQConfig;
import com.patika.bloghubuserservice.producer.dto.SendEmailMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMqProducer {

    private final AmqpTemplate rabbitTemplate;

    private final RabbitMQConfig rabbitMQConfig;

    public void sendEmail(SendEmailMessage sendEmailMessage) {
        rabbitTemplate.convertAndSend(rabbitMQConfig.getExchange(), rabbitMQConfig.getRoutingkey(), sendEmailMessage);

        log.info("message kuyruğa gönderildi. kuyruk:{}, message: {}", rabbitMQConfig.getQueueName(), sendEmailMessage);

    }

}