package com.example.hr.application.business.exception;

import com.example.hr.domain.TcKimlikNo;

@SuppressWarnings("serial")
public class ExistingEmployeeException extends Exception {
	private final TcKimlikNo kimlikNo;

	public ExistingEmployeeException(TcKimlikNo kimlikNo) {
		super("Employee with identity (%s) already exists.".formatted(kimlikNo.getValue()));
		this.kimlikNo = kimlikNo;
	}

	public TcKimlikNo getKimlikNo() {
		return kimlikNo;
	}

}
