package com.example.hr.configuration;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.domain.Employee;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.entity.EmployeeEntity;

@Configuration
public class ModelMapperConfiguration {
	
	private Converter<Employee,EmployeeResponse> EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER = context -> {
		var employee = context.getSource();
		var response = new EmployeeResponse();
		response.setIdentity(employee.getIdentity().getValue());
		response.setFirstName(employee.getFullName().firstName());
		response.setLastName(employee.getFullName().lastName());
		response.setIban(employee.getIban().getValue());
		response.setSalary(employee.getSalary().getValue());
		response.setBirthYear(employee.getBirthYear().value());
		response.setPhoto(employee.getPhoto().getBase64Values());
		response.setJobStyle(employee.getJobStyle().name());
		response.setDepartment(employee.getDepartment().name());
		return response;
	};
	
	private Converter<Employee,EmployeeEntity> EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER = context -> {
		var employee = context.getSource();
		var employeeEntity = new EmployeeEntity();
		employeeEntity.setIdentity(employee.getIdentity().getValue());
		employeeEntity.setFirstName(employee.getFullName().firstName());
		employeeEntity.setLastName(employee.getFullName().lastName());
		employeeEntity.setIban(employee.getIban().getValue());
		employeeEntity.setSalary(employee.getSalary().getValue());
		employeeEntity.setBirthYear(employee.getBirthYear().value());
		employeeEntity.setPhoto(employee.getPhoto().getValues());
		employeeEntity.setJobStyle(employee.getJobStyle());
		employeeEntity.setDepartment(employee.getDepartment());
		return employeeEntity;
	};
	
	private Converter<HireEmployeeRequest,Employee> HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER = context -> {
		var request = context.getSource();
		return new Employee.Builder(request.getIdentity())
	               .fullName(request.getFirstName(), request.getLastName())
	               .iban(request.getIban())
	               .salary(request.getSalary())
	               .jobStyle(request.getJobStyle())
	               .photo(request.getPhoto())
	               .birthYear(request.getBirthYear())
	               .department(request.getDepartment())
	               .build();
	};
	
	private Converter<EmployeeEntity,Employee> EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER = context -> {
		var request = context.getSource();
		return new Employee.Builder(request.getIdentity())
				.fullName(request.getFirstName(), request.getLastName())
				.iban(request.getIban())
				.salary(request.getSalary())
				.jobStyle(request.getJobStyle().name())
				.photo(request.getPhoto())
				.birthYear(request.getBirthYear())
				.department(request.getDepartment().name())
				.build();
	};

	@Bean
	public ModelMapper mapper() {
		var mapper = new ModelMapper();
		mapper.addConverter(EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER ,Employee.class, EmployeeResponse.class);
		mapper.addConverter(HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER ,HireEmployeeRequest.class, Employee.class);
		mapper.addConverter(EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER ,Employee.class, EmployeeEntity.class);
		mapper.addConverter(EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER ,EmployeeEntity.class, Employee.class);
		return mapper;
	}
}
