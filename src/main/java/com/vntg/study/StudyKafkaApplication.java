package com.vntg.study;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.vntg.study.listener.MessageListener;
import com.vntg.study.producer.MessageProducer;

@SpringBootApplication
public class StudyKafkaApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(StudyKafkaApplication.class, args);
//		ConfigurableApplicationContext context = SpringApplication.run(StudyKafkaApplication.class, args);
//		MessageProducer producer = context.getBean(MessageProducer.class);
//		MessageListener listener = context.getBean(MessageListener.class);
//
//		producer.sendMessage("Hello, World!");
//		listener.latch.await(10, TimeUnit.SECONDS);
//
//		for (int i = 0; i < 5; i++) {
//			producer.sendMessageToPartion("Hello To Partioned Topic!", i);
//		}
//		listener.partitionLatch.await(10, TimeUnit.SECONDS);
//
//		producer.sendMessageToFiltered("Hello Baeldung!");
//		producer.sendMessageToFiltered("Hello World!");
//		listener.filterLatch.await(10, TimeUnit.SECONDS);
//
//		String[] arr = {"Greetings", "World!"};
//		producer.sendGreetingMessage(arr);
//		listener.greetingLatch.await(10, TimeUnit.SECONDS);
//
//		context.close();
	}

//	@Bean
//	public MessageProducer messageProducer() {
//		return new MessageProducer();
//	}
//
//	@Bean
//	public MessageListener messageListener() {
//		return new MessageListener();
//	}

}
