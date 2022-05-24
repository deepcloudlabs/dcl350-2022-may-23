package com.example.hr.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class IbanTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/resources/iban-valid.csv")
	void validIbanTest(String value) {
		var iban = Iban.create(value);
		assertAll(() -> assertNotNull(iban), () -> assertEquals(value, iban.getValue()));
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/resources/iban-invalid.csv")
	void invalidIbanTest(String value) {
		assertThrows(IllegalArgumentException.class, () -> Iban.create(value));
	}

}
