package com.example.hr.configuration;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.domain.Employee;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;

@Configuration
public class ModelMapperConfiguration {
	
	private Converter<Employee,EmployeeResponse> EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER = context -> {
		var employee = context.getSource();
		var response = new EmployeeResponse();
		response.setIdentity(employee.getIdentity().getValue());
		
		response.setDepartment(employee.getDepartment().name());
		return response;
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

	@Bean
	public ModelMapper mapper() {
		var mapper = new ModelMapper();
		mapper.addConverter(EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER ,Employee.class, EmployeeResponse.class);
		mapper.addConverter(HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER ,HireEmployeeRequest.class, Employee.class);
		return mapper;
	}
}
