package com.jonak.template.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronJob {

    @Autowired
    private Sender sender;

    public static int counter = 0;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        sender.send("test","testing kafka " + counter++);
    }
}
