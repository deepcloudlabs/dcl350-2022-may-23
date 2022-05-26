package com.example.hr.service.business;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.exception.EmployeeNotFoundException;
import com.example.hr.application.business.exception.ExistingEmployeeException;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.service.HrService;

@Service
public class StandardHrService implements HrService {
	private final HrApplication hrApplication;
	private final ModelMapper modelMapper;

	public StandardHrService(HrApplication hrApplication, ModelMapper modelMapper) {
		this.hrApplication = hrApplication;
		this.modelMapper = modelMapper;
	}

	@Override
	public EmployeeResponse findEmployeeById(String identity) {
		var employee = hrApplication.getEmployee(TcKimlikNo.valueOf(identity)).orElseThrow();
		return modelMapper.map(employee, EmployeeResponse.class);
	}

	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public HireEmployeeResponse hireEmployee(HireEmployeeRequest request) {
		var employee = modelMapper.map(request, Employee.class);
		try {
			hrApplication.hireEmployee(employee);
			return new HireEmployeeResponse("success");
		} catch (ExistingEmployeeException e) {
			return new HireEmployeeResponse("failure",e.getMessage());
		}
	}

	@Override
	@Transactional
	public EmployeeResponse fireEmployee(String identity) {
		try {
			var employee = hrApplication.fireEmployee(TcKimlikNo.valueOf(identity));
			return modelMapper.map(employee, EmployeeResponse.class);
		} catch (EmployeeNotFoundException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

}
