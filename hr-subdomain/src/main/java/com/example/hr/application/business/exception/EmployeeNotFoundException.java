package com.example.hr.application.business.exception;

import com.example.hr.domain.TcKimlikNo;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends Exception {
	private final TcKimlikNo kimlikNo;

	public EmployeeNotFoundException(TcKimlikNo kimlikNo) {
		super("Employee with identity (%s) does not exist.".formatted(kimlikNo.getValue()));
		this.kimlikNo = kimlikNo;
	}

	public TcKimlikNo getKimlikNo() {
		return kimlikNo;
	}

}
