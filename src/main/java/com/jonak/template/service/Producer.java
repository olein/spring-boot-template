package com.jonak.template.service;

import com.google.gson.Gson;
import com.jonak.template.entity.Person;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${jsa.rabbitmq.exchange}")
    private String exchange;

    @Value("${jsa.rabbitmq.routingkey}")
    private String routingKey;


//    public void produceMsg(String msg){
//        amqpTemplate.convertAndSend(exchange, routingKey, msg);
//        System.out.println("Send msg = " + msg);
//    }

    public void sendPersonObject() {
        Person person = new Person();
        person.setName("Fahim");
        person.setMobile("0123456789");

        String msg = new Gson().toJson(person);
        amqpTemplate.convertAndSend(exchange, routingKey, msg);
        System.out.println("Send msg = " + msg);
    }
}
