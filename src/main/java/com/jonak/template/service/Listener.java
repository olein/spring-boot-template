package com.jonak.template.service;

import com.google.gson.Gson;
import com.jonak.template.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.Map;

@Component
public class Listener {

    @Autowired
    JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 5000)
    public void sender() {
        String message = "I am fahim";
        Person person = new Person();
        person.setMobile("1234");
        person.setName("fahim");
        System.out.println("In scheduler");
        jmsTemplate.convertAndSend("inbound.queue", new Gson().toJson(person));
    }

    @JmsListener(destination = "inbound.queue")
    @SendTo("outbound.queue")
    public String receiveMessage(final Message jsonMessage) throws JMSException {
        String messageData = null;
        System.out.println("Received message " + jsonMessage);
        String response = null;
        if (jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) jsonMessage;
            messageData = textMessage.getText();
            Person person = new Gson().fromJson(messageData, Person.class);
            System.out.println("From queue " + person.getName());
        }
        return response;
    }

    @JmsListener(destination = "inbound.topic")
    @SendTo("outbound.topic")
    public String receiveMessageFromTopic(final Message jsonMessage) throws JMSException {
        String messageData = null;
        System.out.println("Received message " + jsonMessage);
        String response = null;
        if (jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) jsonMessage;
            messageData = textMessage.getText();
            System.out.println("From topic " + messageData);
        }
        return response;
    }

}