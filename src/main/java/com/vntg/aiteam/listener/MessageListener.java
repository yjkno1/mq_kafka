package com.vntg.aiteam.listener;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vntg.aiteam.service.AiteamService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MessageListener {

	CountDownLatch latch = new CountDownLatch(3);
	CountDownLatch partitionLatch = new CountDownLatch(2);
	CountDownLatch filterLatch = new CountDownLatch(2);
	CountDownLatch greetingLatch = new CountDownLatch(1);
	
	@Autowired
	AiteamService aiteamService;

	@KafkaListener(topics = "${message.topic.name}", groupId = "foo", containerFactory = "fooKafkaListenerContainerFactory")
	public void listenGroupFoo(String message) {
		log.debug("Received Messasge in group 'foo': " + message);
		latch.countDown();
	}

	@KafkaListener(topics = "${message.topic.name}", groupId = "bar", containerFactory = "barKafkaListenerContainerFactory")
	public void listenGroupBar(String message) {
		log.debug("Received Messasge in group 'bar': " + message);
		latch.countDown();
	}

	@KafkaListener(topics = "${message.topic.name}", containerFactory = "headersKafkaListenerContainerFactory")
	public void listenWithHeaders(@Payload String message,
			@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
		log.debug("Received Messasge: " + message + " from partition: " + partition);
		latch.countDown();
	}

	@KafkaListener(topicPartitions = @TopicPartition(topic = "${partitioned.topic.name}", partitions = { "0",
			"3" }), containerFactory = "partitionsKafkaListenerContainerFactory")
	public void listenToParition(@Payload String message,
			@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
		log.debug("Received Message: " + message + " from partition: " + partition);
		this.partitionLatch.countDown();
	}

	@KafkaListener(topics = "${filtered.topic.name}", containerFactory = "filterKafkaListenerContainerFactory")
	public void listenWithFilter(String message) {
		log.debug("Recieved Message in filtered listener: " + message);
		this.filterLatch.countDown();
	}

	@KafkaListener(topics = "${greeting.topic.name}", containerFactory = "greetingKafkaListenerContainerFactory")
	public void greetingListener(Object greeting) {
		log.debug("Recieved greeting message: " + greeting);
		this.greetingLatch.countDown();
	}
	
	@KafkaListener(topics = "${aiteam.topic.name}", containerFactory = "aiteamKafkaListenerContainerFactory")
	public void aiteamListener(ConsumerRecord<String, Object> data) {
		log.debug("AITEAM LISTENER ::::: ");
		log.debug("Recieved aiteam message: " + data);
		log.debug("TOSTRING ::: "+ data.value().toString());
		ObjectMapper mapper = new ObjectMapper();
		JSONArray jsonData = mapper.convertValue(data.value(), JSONArray.class);
		log.debug(jsonData.toJSONString());
		aiteamService.addPlcData(jsonData);
		this.greetingLatch.countDown();
	}

}