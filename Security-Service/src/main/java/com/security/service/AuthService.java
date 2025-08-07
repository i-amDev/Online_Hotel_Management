package com.security.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.security.exception.InvalidUserCredentialsException;
import com.security.model.Customer;

@Service
public interface AuthService {
	
	public Customer addUser(Customer customer) throws InvalidUserCredentialsException;
	
	public String generateToken(String username, String role);
	
	public boolean validateToken(String token);

	public Customer loadByUsername(String username);

}
