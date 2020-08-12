package com.gautam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gautam.dao.DepartmentDAO;
import com.gautam.model.Department;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentDAO departmentDao;
	
	@Override
	public Integer addDepartment(Department dept) throws Exception {
		Integer deptId=departmentDao.addDepartment(dept);
		if(deptId==null) throw new Exception("Department Not Added!");
		return deptId;
	}

	@Override
	public Integer deleteDepartment(Integer deptId) throws Exception {
		Integer id=departmentDao.deleteDepartment(deptId);
		if(id==null) throw new Exception("Department Not Deleted!");
		return deptId;
	}

	@Override
	public Optional<Department> getDepartment(Integer deptId) throws Exception {
		return departmentDao.getDepartment(deptId);
	}

	@Override
	public Optional<List<Department>> getDepartments() throws Exception {
		return departmentDao.getDepartments();
	}

}
