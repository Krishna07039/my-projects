package com.infy.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.infy.entity.ExcelTable;
import com.infy.service.EmployeeService;

@RestController
public class EmployeeController {

	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/Employees/{endDate}")
	public List<ExcelTable> getEmployees( @PathVariable LocalDate endDate){
		return employeeService.getEmpByendDate(endDate);
	}
	
	@GetMapping("/emp/{date}")
	public List<ExcelTable> getEmployeesByDate( @PathVariable LocalDate date){
		return employeeService.getEmployeesByDate(date);
	}
	
	
 }
