package com.example.hr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.example.hr.domain.Department;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

import lombok.Data;

@Entity
@Table(name="employees")
@Data
public class EmployeeEntity {
	@Id
	@TcKimlikNo
	private String identity;
	@Column(name="fname")
	private String firstName;
	@Column(name="lname")
	private String lastName;
	@Iban
	private String iban;
	private double salary;
	@Column(name="dogum_yili")
	private int birthYear;
	@Lob
	@Column(columnDefinition = "longblob")
	private byte[] photo;
	@Enumerated(EnumType.ORDINAL)
	private Department department;
	@Enumerated(EnumType.STRING)
	private JobStyle jobStyle;
}
