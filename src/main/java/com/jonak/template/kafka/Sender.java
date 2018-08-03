package com.jonak.template.kafka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class Sender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String payload) {
        System.out.println("Sending " + payload + " " + topic);
        kafkaTemplate.send(topic, payload);
    }
}
