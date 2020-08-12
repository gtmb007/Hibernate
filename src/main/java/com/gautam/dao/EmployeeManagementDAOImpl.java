package com.gautam.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gautam.model.Employee;

@Repository("employeeDao")
public class EmployeeManagementDAOImpl implements EmployeeManagementDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public Integer addEmployee(Employee emp) throws Exception {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(emp);
			return emp.getEmpId();
		} catch(HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	public Integer updateEmployee(Integer empId, Employee newEmp) throws Exception {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(newEmp);
			return newEmp.getEmpId();
		} catch(HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	public Integer deleteEmployee(Integer empId) throws Exception {
		Employee emp=sessionFactory.getCurrentSession().get(Employee.class, empId);
		if(emp!=null) {
			sessionFactory.getCurrentSession().delete(emp);
			return empId;
		} else {
			return null;
		}
	}
	
	@Transactional(readOnly=true)
	public Optional<Employee> getEmployee(Integer empId) throws Exception {
		Employee emp=sessionFactory.getCurrentSession().get(Employee.class, empId);
		if(emp==null) return Optional.empty();
		else return Optional.ofNullable(emp);
	}
	
	@Transactional(readOnly=true)
	public Optional<List<Employee>> getEmployees() throws Exception {
		String queryString="select e from Employee e";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(queryString);
		List<Employee> employees=query.getResultList();
		if(employees.isEmpty()) return Optional.empty();
		else return Optional.ofNullable(employees);
	}
	
}