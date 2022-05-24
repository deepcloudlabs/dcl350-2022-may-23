package com.example.hr.application.business;

import java.util.Optional;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.exception.EmployeeNotFoundException;
import com.example.hr.application.business.exception.ExistingEmployeeException;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.domain.event.EmployeeFiredEvent;
import com.example.hr.domain.event.EmployeeHiredEvent;
import com.example.hr.infrastructure.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

public class StandardHrApplication implements HrApplication {
	private EmployeeRepository employeeRepository;
	private EventPublisher eventPublisher;
	

	public StandardHrApplication(EmployeeRepository employeeRepository, EventPublisher eventPublisher) {
		this.employeeRepository = employeeRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public Optional<Employee> getEmployee(TcKimlikNo kimlikNo) {
		return employeeRepository.findById(kimlikNo);
	}

	@Override
	public Employee hireEmployee(Employee employee) throws ExistingEmployeeException {
		var kimlikNo = employee.getIdentity();
		var foundEmployee = employeeRepository.findById(kimlikNo);
		if (foundEmployee.isPresent())
		    throw new ExistingEmployeeException(kimlikNo);  
		eventPublisher.publish(new EmployeeHiredEvent(kimlikNo));
		return employeeRepository.persist(employee);
	}

	@Override
	public Employee fireEmployee(TcKimlikNo kimlikNo) throws EmployeeNotFoundException{
		var foundEmployee = 
				employeeRepository.findById(kimlikNo).orElseThrow(() ->new EmployeeNotFoundException(kimlikNo));  
	    employeeRepository.remove(foundEmployee);	
	    eventPublisher.publish(new EmployeeFiredEvent(kimlikNo));	    
		return foundEmployee;
	}

}
