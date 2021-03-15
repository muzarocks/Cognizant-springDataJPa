package com.cognizant.ormlearn;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.Service.DepartmentService;
import com.cognizant.ormlearn.Service.EmployeeService;
import com.cognizant.ormlearn.Service.SkillService;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;



@SpringBootApplication
public class SecondAndThirdHandsOnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecondAndThirdHandsOnApplication.class);
	
	private static EmployeeService employeeService;
	private static DepartmentService departmentService;
	private static SkillService skillService;
	private static void testGetEmployee()
	{
		LOGGER.info("Start");
		Employee employee = employeeService.get(1);
		LOGGER.debug("Employee:{}",employee);
		LOGGER.debug("Department:{}",employee.getDepartment());
		LOGGER.debug("Skills:{}",employee.getSkillList());
		LOGGER.info("End");
		
	}
	
	private static void testAddEmployee()
	{
		LOGGER.info("Start");
		
		Department dept = new Department();
		
		dept.setId(2);
		dept.setName("HR");
		
		Employee employee = new Employee();
		
		employee.setId(2);
		employee.setDepartment(dept);
		employee.setName("Raju");
		Date dateofBirth = new Date("2019-04-04");
		employee.setDateOfBirth(dateofBirth);
		employee.setPermanent(false);
		employee.setSalary(5000.00);
		
		departmentService.save(dept);
		employeeService.save(employee);
	}
	
	public static void testUpdateEmployee()
	{
		Employee employee = employeeService.get(2);
		Department department = departmentService.get(1);
		
		employee.setDepartment(department);
				
				employeeService.save(employee);
	}
	
	public static void testGetDepartment()
	{
		Department department = departmentService.get(1);
		
		Set<Employee> employeeList = department.getEmployeeList();
		
		  Iterator<Employee> it = employeeList.iterator();
		  
	        while (it.hasNext()) {
	            System.out.println(it.next());
	        }
	}
	
	public static void testAddSkillToEmployee()
	{
		Employee employee = employeeService.get(4);
		Skill skill1 = skillService.get(3);
		Skill skill2 = skillService.get(2);
		
		
		Set<Skill> skillList = employee.getSkillList();
		
		skillList.add(skill1);
		skillList.add(skill2);
		
		employee.setSkillList(skillList);
		
		employeeService.save(employee);
	}
	
	public static void testGetAllPermanentEmployees()
	{
		LOGGER.info("Start");
		List<Employee> employees = employeeService.getAllPermanentEmployees();
		LOGGER.debug("Permanent Employees:{}",employees);
		employees.forEach(e->LOGGER.debug("Skills:{}",e.getSkillList()));
		

		LOGGER.info("End");
	}
	
	
	public static void testGetAverageSalary()
	{
		System.out.println(employeeService.getAverageSalary(1));
	}
	
	public static void testGetAllEmployeesNative()
	{
		LOGGER.info("Start");
		List<Employee> employees = employeeService.getAllPermanentEmployees();
	LOGGER.debug("Permanent Employees:{}",employees);
	employees.forEach(e->LOGGER.debug("Skills:{}",e.getSkillList()));
		
		
		LOGGER.info("End");
	}
	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SecondAndThirdHandsOnApplication.class, args);
		
		employeeService = context.getBean(EmployeeService.class);
		departmentService = context.getBean(DepartmentService.class);
		skillService = context.getBean(SkillService.class);
		testGetEmployee();
	
	testAddEmployee();
		
		testUpdateEmployee();
		
		testGetDepartment();
		
		testAddSkillToEmployee();
		
		testGetAllPermanentEmployees();
		
		testGetAverageSalary();
		
		testGetAllEmployeesNative();
		
		
	}

}
