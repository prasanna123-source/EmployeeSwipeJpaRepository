package com.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.EmployeeDaoImpl;
import com.app.model.Employee;


@RestController
public class EmployeeController {

	@Autowired
	EmployeeDaoImpl employeeDaoRef;

	
	@PostMapping(value = "/saveEmployee")
	public Employee save(@RequestBody Employee emp) {		  
		 return employeeDaoRef.saveEmployee(emp);  
	}
	
	@GetMapping(value = "/getEmployees")
	public List<Employee> listEmployees(Model model) {
		List<Employee> empList = employeeDaoRef.getEmployees();
		if (!empList.isEmpty()) {
			model.addAttribute("employees", empList);
		} else {
			//throw new CustomException("Employee record Not found");
		}
		return empList;
	}
	
	@GetMapping(value = "/getEmployeeByID/{empId}")
	public Optional<Employee> getEmployeeById(@PathVariable(name = "empId") int empId) {

		return employeeDaoRef.getEmployeeById(empId);
	}
	
	@GetMapping(value = "/getEmployeesByLoc/{user}/{location}")
	public List<Employee> getEmployeeByLocation(@PathVariable ("user") String user,@PathVariable("location") String locationName){
		
		if(user.equalsIgnoreCase("admin")) {
		List<Employee> empList=new ArrayList<Employee>();
		empList.add(employeeDaoRef.getAdminEmployeeByLocation(locationName));
		return empList;
		}else {
		return employeeDaoRef.getEmployees();
		}
	}
	@GetMapping(value = "/getEmployeesByDate/{user}/{date}")
	public List<Employee> getEmployeeByDate(@PathVariable("user") String user,@PathVariable("date") String date) {
		if (user.equalsIgnoreCase("admin")) {		
			return employeeDaoRef.getAdminEmployeeByDate(date);
		} else {
			return employeeDaoRef.getEmployees();
		}
	}

}
