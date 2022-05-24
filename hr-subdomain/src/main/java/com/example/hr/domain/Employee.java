package com.example.hr.domain;

import com.example.ddd.annotation.Aggregate;
import com.example.ddd.annotation.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// Ubiquitous Language: Employee, TcKimlikNo, FullName, Iban, Money, ...
// HR-subdomain -> Bounded-Context
// Shared Kernel -> TcKimlikNo, Iban -> Shared Library (Maven)
// Entity Class 
//  i) identity -> identity
// ii) mutable
@Entity(identity = { "identity" })
@Aggregate
@Getter
@Setter
@ToString(exclude = "photo")
@EqualsAndHashCode(of = "identity")
public class Employee {
	private final TcKimlikNo identity;
	private FullName fullName;
	private Iban iban;
	private Money salary;
	private Department department;
	private Photo photo;
	private JobStyle jobStyle;
	private final BirthYear birthYear;

	public Employee(Builder builder) {
		this.identity = builder.identity;
		this.fullName = builder.fullName;
		this.iban = builder.iban;
		this.salary = builder.salary;
		this.department = builder.department;
		this.photo = builder.photo;
		this.jobStyle = builder.jobStyle;
		this.birthYear = builder.birthYear;
	}

	public static class Builder {
		private TcKimlikNo identity;
		private FullName fullName;
		private Iban iban;
		private Money salary;
		private Department department;
		private Photo photo;
		private JobStyle jobStyle;
		private BirthYear birthYear;

		public Builder(String identity) {
			this.identity = TcKimlikNo.valueOf(identity);
		}

		public Builder fullName(String firstName, String lastName) {
			this.fullName = FullName.of(firstName, lastName);
			return this;
		}

		public Builder fullName(FullName fullName) {
			this.fullName = fullName;
			return this;
		}

		public Builder iban(String value) {
			this.iban = Iban.create(value);
			return this;
		}

		public Builder salary(double value, FiatCurrency currency) {
			this.salary = Money.valueOf(value, currency);
			return this;
		}

		public Builder salary(double value) {
			return salary(value, FiatCurrency.TL);
		}

		public Builder department(String value) {
			this.department = Department.valueOf(value);
			return this;
		}

		public Builder photo(byte[] values) {
			this.photo = Photo.makeOf(values);
			return this;
		}

		public Builder photo(String base64Values) {
			this.photo = Photo.makeOf(base64Values);
			return this;
		}

		public Builder jobStyle(String value) {
			this.jobStyle = JobStyle.valueOf(value);
			return this;
		}

		public Builder birthYear(int value) {
			this.birthYear = new BirthYear(value);
			return this;
		}

		public Employee build() {
			// Constraint
			// Validation
			// Business Rule
			// Invariants
			return new Employee(this);
		}
	}

}
