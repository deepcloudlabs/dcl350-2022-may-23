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
import org.springframework.web.context.annotation.RequestScope;

import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.service.HrService;

@RestController
@RequestScope
@RequestMapping("/employees")
@Validated
@CrossOrigin
public class HrApplicationController {
	private HrService hrService;
	
	public HrApplicationController(HrService hrService) {
		this.hrService = hrService;
	}

	@GetMapping("{identity}")
	public EmployeeResponse getEmployeeByIdentity(@PathVariable String identity) {
		return hrService.findEmployeeById(identity);
	}
	
	@PostMapping
	public HireEmployeeResponse hireEmployee(@RequestBody HireEmployeeRequest request) {
		return hrService.hireEmployee(request);
	}
	
	@DeleteMapping("{identity}")
	public EmployeeResponse fireEmployee(@PathVariable String identity) {
		return hrService.fireEmployee(identity);
	}
}
