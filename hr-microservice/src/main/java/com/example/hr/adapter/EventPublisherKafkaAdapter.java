package com.example.hr.adapter;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.hr.domain.event.EmployeeEvent;
import com.example.hr.infrastructure.EventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EventPublisherKafkaAdapter implements EventPublisher {
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;
	

	public EventPublisherKafkaAdapter(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
	}


	@Override
	public void publish(EmployeeEvent event) {
		try {
			kafkaTemplate.send("hr-events", objectMapper.writeValueAsString(event));
		} catch (JsonProcessingException e) {
			System.err.println(e.getMessage());
		}
	}

}
