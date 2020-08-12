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

import com.gautam.model.Department;

@Repository("departmentDao")
public class DepartmentDAOImpl implements DepartmentDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public Integer addDepartment(Department dept) throws Exception {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(dept);
			return dept.getDeptId();
		} catch(HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	@Override
	public Integer deleteDepartment(Integer deptId) throws Exception {
		Department dept=sessionFactory.getCurrentSession().get(Department.class, deptId);
		if(dept!=null) {
			sessionFactory.getCurrentSession().delete(dept);
			return deptId;
		} else {
			return null;
		}
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Department> getDepartment(Integer deptId) throws Exception {
		Department dept=sessionFactory.getCurrentSession().get(Department.class, deptId);
		if(dept==null) return  Optional.empty();
		else return Optional.ofNullable(dept);
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<List<Department>> getDepartments() throws Exception {
		String queryString="select d from Department d";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(queryString);
		List<Department> depts=query.getResultList();
		if(depts.isEmpty()) return Optional.empty();
		else return Optional.ofNullable(depts);
	}

}
