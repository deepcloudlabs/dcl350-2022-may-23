package com.example.hr.adapter;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.hr.domain.event.EmployeeEvent;
import com.example.hr.infrastructure.EventPublisher;

@Service
public class EventPublisherSpringApplicationEventAdapter implements EventPublisher {
	private final ApplicationEventPublisher eventPublisher;

	public EventPublisherSpringApplicationEventAdapter(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	@Override
	public void publish(EmployeeEvent event) {
		eventPublisher.publishEvent(event);
	}

}
