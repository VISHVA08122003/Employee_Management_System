package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmployeeController {
     @Autowired
	private EmployeeService emplo;
	
	@PostMapping("/addEmployee")
	public Employee postEmployee(@RequestBody Employee employee) {
		return emplo.postEmployee(employee);
	}
	
	@GetMapping("/getEmployee")
	public List<Employee> getAllEmployees(){
		return emplo.getAllEmployees();
	}
	
	@DeleteMapping("/delEmployee/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
		try {
			emplo.deleteEmployee(id);
			return new ResponseEntity<>("Employee with id "+ id + "deleted successfully",HttpStatus.OK);
		}
		catch(EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/getallEmployee/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
		Employee employee = emplo.getEmployeeById(id);
		if(employee == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(employee);
	}
	
	@PatchMapping("/updateMap/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
		Employee ue = emplo.updateEmployee(id, employee);
		if(ue == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.ok(ue);
	}
}
