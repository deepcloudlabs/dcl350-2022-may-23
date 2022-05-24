package com.example.hr.application;

import java.util.Optional;

import com.example.hr.application.business.exception.EmployeeNotFoundException;
import com.example.hr.application.business.exception.ExistingEmployeeException;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

public interface HrApplication {
	Optional<Employee> getEmployee(TcKimlikNo kimlikNo);

	Employee hireEmployee(Employee employee) throws ExistingEmployeeException;

	Employee fireEmployee(TcKimlikNo kimlikNo) throws EmployeeNotFoundException;
}
