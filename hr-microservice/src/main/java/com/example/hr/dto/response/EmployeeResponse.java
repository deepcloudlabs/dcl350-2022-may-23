package com.example.hr.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "photo")
@JsonInclude(JsonInclude.Include.NON_NULL) 	
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
