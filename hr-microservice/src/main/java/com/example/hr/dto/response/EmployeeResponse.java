package com.example.hr.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "photo")
public class EmployeeResponse {
	private String identity;
	private String firstName;
	private String lastName;
	private String iban;
	private double salary;
	private int birthYear;
	private String photo;
	private String department;
	private String jobStyle;

}
