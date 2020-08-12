package com.gautam;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Component;

import com.gautam.config.Config;
import com.gautam.model.Department;
import com.gautam.model.Employee;
import com.gautam.model.Location;
import com.gautam.service.DepartmentService;
import com.gautam.service.DepartmentServiceImpl;
import com.gautam.service.EmployeeManagementService;
import com.gautam.service.EmployeeManagementServiceImpl;
import com.gautam.service.LocationService;
import com.gautam.service.LocationServiceImpl;

@Component
public class EmployeeManagement {
	
	static EmployeeManagementService employeeService=null;
	static DepartmentService departmentService=null;
	static LocationService locationService=null;
//	@Autowired
//	@Qualifier("employeeService")
//    EmployeeManagementService employeeService;
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext(Config.class);
		employeeService=applicationContext.getBean("employeeService", EmployeeManagementServiceImpl.class);
		departmentService=applicationContext.getBean("departmentService", DepartmentServiceImpl.class);
		locationService=applicationContext.getBean("locationService", LocationServiceImpl.class);
//		System.out.println("location bean "+locationService);
//		EmployeeManagement reference=applicationContext.getBean(EmployeeManagement.class);
//		System.out.println("emang"+reference);
		new EmployeeManagement().myFunc();
		applicationContext.close();
	}
	
	public void myFunc() {
//		System.out.println(employeeService);
		while(true) {
			System.out.println("Enter \n 1.) Add Employee \n 2.) Update Employee \n 3.) Delete Employee \n 4.) Get Employee By Id \n 5.) Get All Employees\n "
					+ "6.) Add Department \n 7.) Delete Department \n 8.) Get Department By Id \n 9.) Get All Departments \n "
					+ "10.) Add Location \n 11.) Update Location \n 12.) Delete Location \n 13.) Get Location By Id \n 14.) Get All Locations \n 0.) Exit \n");
			int option=sc.nextInt();
			switch(option) {
				case 1: addEmployee();
				break;
				case 2 : updateEmployee();
				break;
				case 3 : deleteEmployee();
				break;
				case 4 : getEmployee();
				break;
				case 5 : getEmployees();
				break;
				case 6 : addDepartment();
				break;
				case 7 : deleteDepartment();
				break;
				case 8 : getDepartment();
				break;
				case 9 : getDepartments();
				break;
				case 10 : addLocation();
				break;
				case 11 : updateLocation();
				break;
				case 12 : deleteLocation();
				break;
				case 13 : getLocation();
				break;
				case 14 : getLocations();
				break;
				case 0 : System.exit(0);;
				default :
					System.out.println("Select Proper Choice");
			}
		}
	}
	
	public void addEmployee() {
		Employee emp=new Employee();
//		emp.setEmpId(2);
		emp.setfName("Gautam");
		emp.setlName("Bharadwaj");
		emp.setSalary(500000);
		try {
			Integer id=employeeService.addEmployee(emp);
			System.out.println("Employee Added Successfully with id : "+id);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateEmployee() {
		Employee emp=new Employee();
		System.out.println("Enter EmpId : ");
		emp.setEmpId(sc.nextInt());
		emp.setfName("Rohit");
		emp.setlName("Kumar");
		emp.setSalary(600000);
		try {
			Integer id=employeeService.updateEmployee(emp.getEmpId(), emp);
			System.out.println("Employee Updated Successfully with id : "+id);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteEmployee() {
		try {
			System.out.println("Enter EmpId : ");
			Integer id=employeeService.deleteEmployee(sc.nextInt());
			System.out.println("Employee Deleted Successfully with id : "+id);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void getEmployee() {
		try {
			System.out.println("Enter EmpId : ");
			Optional<Employee> emp=employeeService.getEmployee(sc.nextInt());
			if(emp.isPresent()) {
				System.out.println(emp.get());
			} else {
				System.out.println("Employee Not Found!");
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void getEmployees() {
		try {
			Optional<List<Employee>> empList=employeeService.getEmployees();
			if(empList.isPresent()) {
				for(Employee e : empList.get())
					System.out.println(e);
			} else {
				System.out.println("No Employees Found!");
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addDepartment() {
		Department dept=new Department();
		dept.setDeptName("Information Technology");
		Set<Employee> employees=new HashSet<>();
		Employee emp1=new Employee();
		emp1.setfName("Gautam");
		emp1.setlName("Bharadwaj");
		emp1.setSalary(700000);
		employees.add(emp1);
		Employee emp2=new Employee();
		emp2.setfName("Rohit");
		emp2.setlName("Kumar");
		emp2.setSalary(800000);
		employees.add(emp2);
		dept.setEmployees(employees);
		Set<Location> locations=new HashSet<>();
		Location loc1=new Location();
		loc1.setCity("SaltLake, Kolkata");
		loc1.setCountry("India");
		locations.add(loc1);
		Location loc2=new Location();
		loc2.setCity("Haringhata, Nadia");
		loc2.setCountry("India");
		locations.add(loc2);
		dept.setLocations(locations);
		try {
			Integer deptId=departmentService.addDepartment(dept);
			System.out.println("Department Added Successfully with id : "+deptId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteDepartment() {
		try {
			System.out.println("Enter DeptId : ");
			Integer deptId=departmentService.deleteDepartment(sc.nextInt());
			System.out.println("Department Deleted Successfully with id : "+deptId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void getDepartment() {
		try {
			System.out.println("Enter DeptId : ");
			Optional<Department> dept=departmentService.getDepartment(sc.nextInt());
			if(dept.isPresent()) {
				System.out.println(dept.get());
			} else {
				System.out.println("Department Not Found!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void getDepartments() {
		try {
			Optional<List<Department>> depts=departmentService.getDepartments();
			if(depts.isPresent()) {
				for(Department dept : depts.get()) {
					System.out.println(dept);
				}
			} else {
				System.out.println("No Department Found!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addLocation() {
		try {
			Location loc=new Location();
			loc.setCity("Patna");
			loc.setCountry("India");
			Integer locId=locationService.addLocation(loc);
			System.out.println("Location Added Successfully with id : "+locId);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateLocation() {
		System.out.println("Enter LocId : ");
		Location loc=new Location();
		loc.setLocId(sc.nextInt());
		loc.setCity("Kolkata");
		loc.setCountry("India");
		try {
			Integer locId=locationService.updateLocation(loc.getLocId(), loc);
			System.out.println("Location Updated Successfully with id : "+locId);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteLocation() {
		try {
			System.out.println("Enter LocId : ");
			Integer locId=locationService.deleteLocation(sc.nextInt());
			System.out.println("Location Deleted Successfully with id : "+locId);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void getLocation() {
		try {
			System.out.println("Enter LocId : ");
			Optional<Location> loc=locationService.getLocation(sc.nextInt());
			if(loc.isPresent()) {
				System.out.println(loc.get());
			} else {
				System.out.println("Location Not Found!");
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void getLocations() {
		try {
			Optional<List<Location>> locs=locationService.getLocations();
			if(locs.isPresent()) {
				for(Location l : locs.get())
					System.out.println(l);
			} else {
				System.out.println("No Locations Found!");
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
