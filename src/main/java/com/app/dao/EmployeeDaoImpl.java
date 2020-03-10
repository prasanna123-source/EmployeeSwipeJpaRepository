package com.app.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Repository.EmployeeRepository;
import com.app.model.Employee;

@Service
public class EmployeeDaoImpl {

	 @Autowired
	 EmployeeRepository dao;
	
	public Employee saveEmployee(Employee employee) {
		return dao.save(employee);
         
	}
	public List<Employee> getEmployees() {
		return dao.findAll();
		
	}
	
	public Optional<Employee> getEmployeeById(int empId) {
		return dao.findById(empId);			
		
    }
	
	public Employee getAdminEmployeeByLocation(String locationName) {
		return dao.findByLocation(locationName).get();
		
	}
	
	
	public List<Employee> getAdminEmployeeByDate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");		 			
		 Date date1 = null;
		try {
			date1 = formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dao.findByDate(date1);
		 
		
	}	
	
}
