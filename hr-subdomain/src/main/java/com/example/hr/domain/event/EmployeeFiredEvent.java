package com.example.hr.domain.event;

import com.example.hr.domain.TcKimlikNo;

public class EmployeeFiredEvent extends EmployeeEvent {

	public EmployeeFiredEvent(TcKimlikNo identity) {
		super(identity);
	}

}
