package com.jonak.template.kafka;

import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

public class Receiver {


    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "${kafka.topic.helloworld}")
    public void receive(String payload) {
        latch.countDown();
        System.out.println("Receiving 1 " + payload);
    }

    @KafkaListener(topics = "test")
    public void receive2(String payload) {
        latch.countDown();
        System.out.println("Receiving 2 " + payload);
    }
}
