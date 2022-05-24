package com.example.hr.domain;

import java.util.Objects;

import com.example.ddd.annotation.Aggregate;
import com.example.ddd.annotation.Entity;

// Ubiquitous Language: Employee, TcKimlikNo, FullName, Iban, Money, ...
// HR-subdomain -> Bounded-Context
// Shared Kernel -> TcKimlikNo, Iban -> Shared Library (Maven)
// Entity Class 
//  i) identity -> identity
// ii) mutable
@Entity(identity = { "identity" })
@Aggregate
public class Employee {
	private final TcKimlikNo identity;
	private FullName fullName;
	private Iban iban;
	private Money salary;
	private Department department;
	private Photo photo;
	private JobStyle jobStyle;
	private final BirthYear birthYear;

	private Employee(TcKimlikNo identity, FullName fullName, Iban iban, Money salary, Department department,
			Photo photo, JobStyle jobStyle, BirthYear birthYear) {
		this.identity = identity;
		this.fullName = fullName;
		this.iban = iban;
		this.salary = salary;
		this.department = department;
		this.photo = photo;
		this.jobStyle = jobStyle;
		this.birthYear = birthYear;
	}

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

	public TcKimlikNo getIdentity() {
		return identity;
	}

	public BirthYear getBirthYear() {
		return birthYear;
	}

	@Override
	public int hashCode() {
		return Objects.hash(identity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(identity, other.identity);
	}

	@Override
	public String toString() {
		return "Employee [identity=" + identity + ", fullName=" + fullName + ", iban=" + iban + ", salary=" + salary
				+ ", department=" + department + ", jobStyle=" + jobStyle + ", birthYear=" + birthYear + "]";
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
