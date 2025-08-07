package com.guest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.guest.model.Guest_Model;

public interface Guest_Repo extends MongoRepository<Guest_Model, String>{
	
	public Guest_Model findByEmail(String email);
	public Guest_Model findByPhoneNumber(long phoneNumber);
	public String deleteByPhoneNumber(long phoneNumber);

}
