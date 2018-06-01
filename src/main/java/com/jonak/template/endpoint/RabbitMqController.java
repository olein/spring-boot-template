package com.jonak.template.endpoint;

import com.jonak.template.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMqController {

    @Autowired
    Producer producer;

    @RequestMapping("/send")
    public String sendMsg(@RequestParam("msg") String msg) {
        //producer.produceMsg(msg);
        producer.sendPersonObject();
        return "Done";
    }

}
