package com.example.hr.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "photo")
public class HireEmployeeRequest {
	@TcKimlikNo
	private String identity;
	@Size(min = 3)
	private String firstName;
	@Size(min = 2)
	private String lastName;
	@Iban
	private String iban;
	@Min(4500)
	private double salary;
	@Max(2004)
	private int birthYear;
	@NotBlank
	private String photo;
	@NotNull
	private String department;
	private String jobStyle;
}
