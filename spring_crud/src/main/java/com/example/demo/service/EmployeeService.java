package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
     @Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee postEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	

	public void deleteEmployee(Long id) {
		// TODO Auto-generated method stub
		if(!employeeRepository.existsById(id)) {
			throw new EntityNotFoundException("Employee with id "+id+" not found");
		}
		employeeRepository.deleteById(id);
	}
	

	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id).orElse(null);
	}
	

	public Employee updateEmployee(Long id, Employee employee) {
		Optional<Employee> oe = employeeRepository.findById(id);
		if(oe.isPresent()) {
			Employee existingemp = oe.get();
			
			existingemp.setEmail(employee.getEmail());
			existingemp.setName(employee.getName());
			existingemp.setDepartment(employee.getDepartment());
			existingemp.setPhone(employee.getPhone());
			return employeeRepository.save(existingemp);
		}
		return null;
	}
}
