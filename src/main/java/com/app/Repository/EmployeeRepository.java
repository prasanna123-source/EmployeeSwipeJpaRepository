package com.app.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
   @Query("select t from Employee t where t.locationName= :locationName")
    Optional<Employee> findByLocation(@Param("locationName") String locationName);
	  
   @Query("select t from Employee t where t.createDate= :createDate")
	public List<Employee> findByDate(@Param("createDate") Date date1);
}
