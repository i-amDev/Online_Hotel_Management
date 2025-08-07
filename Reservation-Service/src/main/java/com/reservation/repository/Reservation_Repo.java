package com.reservation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.reservation.model.Reservation_Model;

@Repository
public interface Reservation_Repo extends MongoRepository<Reservation_Model, String>{
	
	public Reservation_Model findByPhoneNumber(long phoneNumber);
	
	public String deleteByPhoneNumber(long phoneNumber);
	

}
