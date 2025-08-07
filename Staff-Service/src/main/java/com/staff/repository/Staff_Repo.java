package com.staff.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.staff.model.Staff_Model;

@Repository
public interface Staff_Repo extends MongoRepository<Staff_Model, String>{
	
	public Staff_Model findByEmail(String email);
	
	public String deleteByEmail(String email);

}
