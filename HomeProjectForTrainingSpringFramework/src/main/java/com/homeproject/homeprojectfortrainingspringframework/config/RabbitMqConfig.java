package com.homeproject.homeprojectfortrainingspringframework.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${sample.rabbitmq.exchange}")
    String exchange;

    @Value("${sample.rabbitmq.firstQueue}")
    String firstQueueName;
    @Value("${sample.rabbitmq.secondQueue}")
    String secondQueueName;
    @Value("${sample.rabbitmq.thirdQueue}")
    String thirdQueueName;

    @Value("${sample.rabbitmq.firstRoutingKey}")
    String firstRoutingKey;
    @Value("${sample.rabbitmq.secondRoutingKey}")
    String secondRoutingKey;
    @Value("${sample.rabbitmq.thirdRoutingKey}")
    String thirdRoutingKey;


    @Bean
    DirectExchange exchange(){return new DirectExchange(exchange);}

    @Bean
    Queue firstStepQueue(){return new Queue(firstQueueName);}

    @Bean
    Queue secondStepQueue(){return new Queue("secondQueueName");}

    @Bean
    Queue thirdStepQueue(){return new Queue("thirdQueueName");}

    @Bean
    Binding firstBinding(Queue firstStepQueue, DirectExchange exchange){
        return BindingBuilder.bind(firstStepQueue).to(exchange).with(firstRoutingKey);
    }

    @Bean
    Binding secondBinding(Queue secondStepQueue, DirectExchange exchange){
        return BindingBuilder.bind(secondStepQueue).to(exchange).with(secondRoutingKey);
    }

    @Bean
    Binding thirdBinding(Queue thirdStepQueue, DirectExchange exchange){
        return BindingBuilder.bind(thirdStepQueue).to(exchange).with(thirdRoutingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter(){return new Jackson2JsonMessageConverter();}
}
