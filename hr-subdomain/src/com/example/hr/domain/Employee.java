package com.example.hr.domain;

import com.example.ddd.annotation.Entity;

// Ubiquitous Language: Employee, TcKimlikNo, FullName, Iban, Money, ...
// HR-subdomain -> Bounded-Context
// Shared Kernel -> TcKimlikNo, Iban -> Shared Library (Maven)
// Entity Class 
//  i) identity -> identity
// ii) mutable
@Entity(identity = {"identity"})
public class Employee {
	private final TcKimlikNo identity;
	private FullName fullName;
	private Iban iban;
	private Money salary;
	private Department department;
	private Photo photo;
	private JobStyle jobStyle;
	private final BirthYear birthYear;
	private String email;
	
	public Employee(TcKimlikNo identity, FullName fullName, Iban iban, Money salary, Department department, Photo photo,
			JobStyle jobStyle, BirthYear birthYear) {
		this.identity = identity;
		this.fullName = fullName;
		this.iban = iban;
		this.salary = salary;
		this.department = department;
		this.photo = photo;
		this.jobStyle = jobStyle;
		this.birthYear = birthYear;
	}

	public FullName getFullName() {
		return fullName;
	}

	public void setFullName(FullName fullName) {
		this.fullName = fullName;
	}

	public Iban getIban() {
		return iban;
	}

	public void setIban(Iban iban) {
		this.iban = iban;
	}

	public Money getSalary() {
		return salary;
	}

	public void setSalary(Money salary) {
		this.salary = salary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public JobStyle getJobStyle() {
		return jobStyle;
	}

	public void setJobStyle(JobStyle jobStyle) {
		this.jobStyle = jobStyle;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TcKimlikNo getIdentity() {
		return identity;
	}

	public BirthYear getBirthYear() {
		return birthYear;
	}
	
	
	
	
}
