package com.example.hr.infrastructure;

import com.example.hr.domain.event.EmployeeEvent;

public interface EventPublisher {

	void publish(EmployeeEvent event);

}
