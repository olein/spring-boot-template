package com.jonak.template.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonak.template.entity.Person;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Consumer {

//    @RabbitListener(queues="${jsa.rabbitmq.queue}")
//    public void recievedMessage(String msg) {
//        System.out.println("Recieved Message1: " + msg);
//    }

    @RabbitListener(queues="${jsa.rabbitmq.queue}")
    public void recievedPersonObject(String msg) {

        ObjectMapper mapper = new ObjectMapper();
        Person person = null;
        try {
            person = mapper.readValue(msg, Person.class);
            System.out.println(person.getName());
            System.out.println("Recieved Message2: " + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
