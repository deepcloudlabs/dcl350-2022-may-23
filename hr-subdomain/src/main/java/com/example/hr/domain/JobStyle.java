package com.example.hr.domain;

import com.example.ddd.annotation.ValueObject;

@ValueObject
public enum JobStyle {
	FULL_TIME(100), PART_TIME(200);

	private int id;

	private JobStyle(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
