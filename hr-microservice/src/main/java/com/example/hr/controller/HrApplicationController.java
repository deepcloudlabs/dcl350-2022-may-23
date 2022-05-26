package com.example.hr.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.service.HrService;
import com.example.validation.TcKimlikNo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@RestController
//@RequestScope
@RequestMapping("/employees")
@Validated
@CrossOrigin
@OpenAPIDefinition(
		info = @Info(
			title = "HR RESTful Service", 
			version = "1.0",
			description = "HR REST API enables clients to access hr sub-domain capabilities through http.",
			license = @License(name = "MIT License"),
			contact = @Contact(email = "binnur.kurt@gmail.com",name = "Binnur Kurt")
		)
)
public class HrApplicationController {
	private HrService hrService;
	
	public HrApplicationController(HrService hrService) {
		this.hrService = hrService;
		System.err.println(hrService.getClass());
	}
	
	@Operation(summary = "Get employee information by its identity")
	@GetMapping("{identity}")
	public EmployeeResponse getEmployeeByIdentity(@PathVariable @TcKimlikNo String identity) {
		return hrService.findEmployeeById(identity);
	}
	
	@Operation(summary = "Hire an employee")
	@PostMapping
	public HireEmployeeResponse hireEmployee(@RequestBody @Validated HireEmployeeRequest request) {
		return hrService.hireEmployee(request);
	}
	
	@Operation(summary = "Fire an employee")
	@DeleteMapping("{identity}")
	public EmployeeResponse fireEmployee(@PathVariable String identity) {
		return hrService.fireEmployee(identity);
	}
}
