package com.example.hr.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class HrKafkaConsumerService {

	@KafkaListener(topics = "${event.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void listenHrEvents(String event) {
		System.err.println("New hr event has arrived from kafka topic(%s): %s".formatted("hr-events", event));
	}
}
