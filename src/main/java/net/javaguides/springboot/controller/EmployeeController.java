package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins="http://localhost:4200")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees()
	{
		return employeeRepository.findAll();
	}
	
	//create empoloyee rest api
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee)
	{
		return employeeRepository.save(employee);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id)
	{
		Employee employee= employeeRepository.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("Employee not exits with id: "+id));
		
		return  ResponseEntity.ok(employee);
	}
	
	//update employee restAPI
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails)
	{
		Employee employee= employeeRepository.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("Employee not exits with id: "+id));
				
				
				employee.setFirstname(employeeDetails.getFirstname());
				employee.setLastname(employeeDetails.getLastname());
				employee.setEmailId(employeeDetails.getEmailId());
				
				Employee updatedEmployee= employeeRepository.save(employee);
				
				return ResponseEntity.ok(updatedEmployee);
	}

}









