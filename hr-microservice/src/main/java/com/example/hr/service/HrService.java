package com.example.hr.service;

import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;

public interface HrService {

	EmployeeResponse findEmployeeById(String identity);

	HireEmployeeResponse hireEmployee(HireEmployeeRequest request);

	EmployeeResponse fireEmployee(String identity);

}
