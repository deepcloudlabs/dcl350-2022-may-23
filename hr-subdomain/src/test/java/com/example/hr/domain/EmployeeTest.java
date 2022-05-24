package com.example.hr.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class EmployeeTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/resources/employees.csv")
	void createEmployeeSuccessfully(String identity,String firstName,String lastName,
			String iban,double salary,String currency,String jobStyle,int birthYear,String department,String base64Photo) {
	    // 1. Test Setup/Fixture -> CUT
		// 2. Call Exercise Method -> MUT
		var emp = new Employee.Builder(identity)
				               .fullName(firstName, lastName)
				               .iban(iban)
				               .salary(salary, FiatCurrency.valueOf(currency))
				               .jobStyle(jobStyle)
				               .photo(base64Photo)
				               .birthYear(birthYear)
				               .department(department)
				               .build();
		// 3. Verification
		assertAll(
			() -> assertEquals(identity, emp.getIdentity().getValue()),
			() -> assertEquals(firstName, emp.getFullName().firstName()),
			() -> assertEquals(lastName, emp.getFullName().lastName()),
			() -> assertEquals(iban, emp.getIban().getValue()),
			() -> assertEquals(salary, emp.getSalary().getValue()),
			() -> assertEquals(currency, emp.getSalary().getCurrency().name()),
			() -> assertEquals(department, emp.getDepartment().name()),
			() -> assertEquals(jobStyle, emp.getJobStyle().name()),
			() -> assertEquals(birthYear, emp.getBirthYear().value()),
			() -> assertEquals(base64Photo, emp.getPhoto().getBase64Values())
		);
		// 4. Tear-down
	}

}
