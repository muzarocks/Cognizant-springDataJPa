package com.cognizant.ormlearn.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn.Repository.EmployeeRepository;
import com.cognizant.ormlearn.model.Employee;


@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Transactional
	public Employee get(int id)
	{
		
		return employeeRepository.findById(id).get();
		
		
	}
	
	@Transactional
	public void save(Employee employee)
	{
		
		employeeRepository.save(employee);
	}
	
	@Transactional
	public List<Employee> getAllPermanentEmployees()
	{
		return employeeRepository.getAllPermanentEmployees();
	}
	
	
	public double getAverageSalary(int id)
	{
		return employeeRepository.getAverageSalary( id);
	}
	
	@Transactional
	public List<Employee> getAllEmployeesNative()
	{
		return employeeRepository.getAllEmployeesNative();
	}
}
