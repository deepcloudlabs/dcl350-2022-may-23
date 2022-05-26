package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.repository.EmployeeDocumentRepository;
import com.example.hr.repository.EmployeeRepository;

@Repository
@ConditionalOnProperty(name = "persistence.target.mongo")
public class EmployeeRepositoryMongoAdapter implements EmployeeRepository {
	private final EmployeeDocumentRepository empRepo;
	private final ModelMapper modelMapper;

	public EmployeeRepositoryMongoAdapter(EmployeeDocumentRepository empRepo, ModelMapper modelMapper) {
		this.empRepo = empRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public Optional<Employee> findById(TcKimlikNo kimlikNo) {
		return empRepo.findById(kimlikNo.getValue()).map(emp -> modelMapper.map(emp, Employee.class));
	}

	@Override
	public Employee persist(Employee employee) {
		empRepo.save(modelMapper.map(employee, EmployeeDocument.class));
		return employee;
	}

	@Override
	public void remove(Employee employee) {
		empRepo.delete(modelMapper.map(employee, EmployeeDocument.class));
	}

}
