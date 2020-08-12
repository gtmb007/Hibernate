package com.gautam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gautam.dao.EmployeeManagementDAO;
import com.gautam.model.Employee;

@Service("employeeService")
public class EmployeeManagementServiceImpl implements EmployeeManagementService {
	
	@Autowired
	EmployeeManagementDAO employeeDao;
	
	public Integer addEmployee(Employee emp) throws Exception {
		Integer empId=employeeDao.addEmployee(emp);
		if(empId==null) throw new Exception("Employee Not Added");
		return empId;
	}
	
	public Integer deleteEmployee(Integer empId) throws Exception {
		Integer id=employeeDao.deleteEmployee(empId);
		if(id==null) throw new Exception("Employee Not Deleted");
		return id;
	}
	
	public Integer updateEmployee(Integer empId, Employee newEmp) throws Exception {
		Integer id=employeeDao.updateEmployee(empId, newEmp);
		if(id==null) throw new Exception("Employee Not Update");
		return empId;
	}
	
	public Optional<Employee> getEmployee(Integer empId) throws Exception {
		return employeeDao.getEmployee(empId);
	}
	
	public Optional<List<Employee>> getEmployees() throws Exception {
		return employeeDao.getEmployees();
	}
	
}
