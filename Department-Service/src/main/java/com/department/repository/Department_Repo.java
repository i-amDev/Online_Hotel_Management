package com.department.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.department.model.Department_Model;

@Repository
public interface Department_Repo extends MongoRepository<Department_Model, Long>{
	
	public Department_Model findByDepartmentName(String departmentName);
	
	public String deleteByDepartmentName(String departmentName);

}
