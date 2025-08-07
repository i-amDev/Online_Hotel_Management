package com.security.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.security.model.Customer;

@Repository
public interface AuthRepo extends MongoRepository<Customer, String>{

	Optional<Customer> findByUsername(String username);
	
	Customer findByEmail(String email);
	
	Customer findByPhoneNumber(long phoneNumber);

}
