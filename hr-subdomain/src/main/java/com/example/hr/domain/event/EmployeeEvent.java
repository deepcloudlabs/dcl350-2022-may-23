package com.example.hr.domain.event;

import com.example.hr.domain.TcKimlikNo;

import lombok.Getter;

@Getter
public class EmployeeEvent {
	private final TcKimlikNo identity;

	public EmployeeEvent(TcKimlikNo identity) {
		this.identity = identity;
	}
	
}
