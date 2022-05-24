package com.example.hr.domain;

import java.util.Base64;
import java.util.Objects;

import com.example.ddd.annotation.ValueObject;

@ValueObject
public class Photo {
	private final byte[] values;

	private Photo(byte[] values) {
		this.values = values;
	}

	public byte[] getValues() {
		return values;
	}
	
	public String getBase64Values() {
		return Base64.getEncoder().encodeToString(values);
	}
	
	public static Photo makeOf(byte[] values) {
		if (Objects.isNull(values))
			throw new IllegalArgumentException("Photo values must be non null.");
		return new Photo(values);
	}
	
	public static Photo makeOf(String base64Values) {
		if (Objects.isNull(base64Values))
			throw new IllegalArgumentException("Photo values must be non null.");
		return makeOf(Base64.getDecoder().decode(base64Values));
	}
	
}
