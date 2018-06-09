package com.jonak.template;

import com.jonak.template.service.Listener;
import com.jonak.template.service.MessageReceiver;
import com.jonak.template.service.MessageSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.jms.JMSException;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@ComponentScan("com.jonak.template")
@EnableJpaRepositories
@EnableScheduling
public class TemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemplateApplication.class, args);
		MessageSender sender = new MessageSender();
		MessageReceiver receiver = new MessageReceiver();

		try {
			sender.sendMessage();
			receiver.receiveMessage();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}



