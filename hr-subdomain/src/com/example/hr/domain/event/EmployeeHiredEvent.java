package com.example.hr.domain.event;

import com.example.hr.domain.TcKimlikNo;

public class EmployeeHiredEvent extends EmployeeEvent {

	public EmployeeHiredEvent(TcKimlikNo identity) {
		super(identity);
	}

}
