package com.example.hr.domain.event;

import com.example.hr.domain.TcKimlikNo;

public class EmployeeEvent {
	private final TcKimlikNo identity;

	public EmployeeEvent(TcKimlikNo identity) {
		this.identity = identity;
	}

	public TcKimlikNo getIdentity() {
		return identity;
	}
	
}
