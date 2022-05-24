package com.example.hr.domain;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses(value = {
	EmployeeTest.class,
	IbanTest.class,
	TcKimlikNoTest.class
})
public class HrTestSuite {

}
