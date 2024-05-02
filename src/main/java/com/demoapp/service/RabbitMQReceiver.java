package com.demoapp.service;

import com.demoapp.model.Employee;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQReceiver {

    @Value("${rabbitmq.queue}")
    String  queueName;

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @RabbitListener(queues = "${rabbitmq.queue}", id = "listener")
    public void receiver(final Employee employee) {
        System.out.println("Receiver invoked - " +
                "Consuming Message with Employee Identifier : " +
                employee.getEmpId() + " and Message : " + employee.toString() +
                " from the Queue: " + queueName);
    }
}
