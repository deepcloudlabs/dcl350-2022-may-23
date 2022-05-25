package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.entity.EmployeeEntity;
import com.example.hr.repository.EmployeeEntityRepository;
import com.example.hr.repository.EmployeeRepository;

@Repository
public class EmployeeRepositoryJpaAdapter implements EmployeeRepository {
	private final EmployeeEntityRepository empRepo;
	private final ModelMapper modelMapper;
	
	public EmployeeRepositoryJpaAdapter(EmployeeEntityRepository empRepo, ModelMapper modelMapper) {
		this.empRepo = empRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public Optional<Employee> findById(TcKimlikNo kimlikNo) {
		return empRepo.findById(kimlikNo.getValue())
				      .map(emp -> modelMapper.map(emp, Employee.class));
	}

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
	public Employee persist(Employee employee) {
		empRepo.save(modelMapper.map(employee, EmployeeEntity.class));
		return employee;
	}

	@Override
	@Transactional
	public void remove(Employee employee) {
		empRepo.delete(modelMapper.map(employee, EmployeeEntity.class));
	}

}
