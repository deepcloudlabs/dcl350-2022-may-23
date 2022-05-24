package com.example.hr.domain;

import java.util.Objects;

import com.example.ddd.annotation.ValueObject;

@ValueObject
public record FullName(String firstName, String lastName) {
	public static FullName of(String firstName, String lastName) {
		Objects.nonNull(firstName);
		Objects.nonNull(lastName);
		return new FullName(firstName, lastName);
	}
}
