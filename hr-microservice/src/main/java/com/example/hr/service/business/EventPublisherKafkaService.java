package com.example.hr.service.business;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.hr.domain.event.EmployeeEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EventPublisherKafkaService {
	@Value("${hr.event.topic.name}")
	private String hrEventTopicName;
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;

	public EventPublisherKafkaService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
	}

	@EventListener
	public void listenDomainEvent(EmployeeEvent event) {
		try {
			kafkaTemplate.send(hrEventTopicName, objectMapper.writeValueAsString(event));
		} catch (JsonProcessingException e) {
			System.err.println(e.getMessage());
		}
	}

}
