package com.example.hr.domain;

import java.util.Objects;

import com.example.ddd.annotation.ValueObject;

@ValueObject
public class Money {
	private final double value;
	private final FiatCurrency currency;

	private Money(double value, FiatCurrency currency) {
		this.value = value;
		this.currency = currency;
	}

	private Money(double value) {
		this(value, FiatCurrency.TL);
	}

	public double getValue() {
		return value;
	}

	public FiatCurrency getCurrency() {
		return currency;
	}

	public static Money valueOf(double value) {
		return valueOf(value, FiatCurrency.TL);
	}

	public static Money valueOf(double value, FiatCurrency currency) {
		if (Objects.isNull(currency))
			throw new IllegalArgumentException("Currenct must be a non null value.");
		if (value <= 0.)
			throw new IllegalArgumentException("Money value must be positive.");
		return new Money(value, currency);
	}
}
