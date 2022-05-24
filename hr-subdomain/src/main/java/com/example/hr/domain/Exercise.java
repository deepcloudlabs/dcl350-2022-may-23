package com.example.hr.domain;

import java.util.List;

@SuppressWarnings("unused")
public class Exercise {
	public static void main(String[] args) {
		Integer i = Integer.valueOf(42);
		Integer j = 42;
		Integer m = 549;
		Integer n = 549;
		System.err.println("i==j ? " + (i == j));
		System.err.println("m==n ? " + (m == n));
		var numbers = List.of(4, 8, 15, 16, 23, 42);
		var jobStyle = JobStyle.valueOf("FULL_TIME");
		for (var js : JobStyle.values())
			System.err.println(js.name() + ": " + js.ordinal() + " : " + js.getId());
	}
}
