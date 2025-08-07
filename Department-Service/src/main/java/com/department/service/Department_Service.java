package com.department.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.department.exception.DepartmentAlreadyExistException;
import com.department.exception.DepartmentNameNotFoundException;
import com.department.model.Department_Model;

@Service
public interface Department_Service {
	
	public List<Department_Model> getAll();
	public Department_Model getByDepartmentName(String departmentName) throws DepartmentNameNotFoundException;
	public Department_Model addDepartment(Department_Model department) throws DepartmentAlreadyExistException;
	public String deleteDepartment(String departmentName) throws DepartmentNameNotFoundException;
	public Department_Model updateDescription(String departmentName,String value);
	public Department_Model updateNoOfEmployees(String departmentName,int value) throws DepartmentNameNotFoundException;

}
