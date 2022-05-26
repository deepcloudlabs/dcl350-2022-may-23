package com.example.hr.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.hr.domain.Department;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

import lombok.Data;

@Document(collection="employees")
@Data
public class EmployeeDocument {
	@TcKimlikNo
	@Id
	private String identity;
	@Field(name="fname")
	private String firstName;
	@Field(name="lname")
	private String lastName;
	@Iban
	private String iban;
	private double salary;
	@Field(name="dogum_yili")
	private int birthYear;
	private String photo;
	private Department department;
	private JobStyle jobStyle;
}
