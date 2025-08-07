package com.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.exception.InvalidUserCredentialsException;
import com.security.model.Customer;
import com.security.repository.AuthRepo;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthRepo authRepo;

	@Override
	public Customer addUser(Customer customer) throws InvalidUserCredentialsException {
		Optional<Customer> cust1 = authRepo.findByUsername(customer.getUsername());
		Customer cust2 = authRepo.findByEmail(customer.getEmail());
		Customer cust3 = authRepo.findByPhoneNumber(customer.getPhoneNumber());
		if(cust1.isPresent()) {
			throw new InvalidUserCredentialsException("User already exists by this username");
		}
		if(cust2!=null) {
			throw new InvalidUserCredentialsException("User already exists by this email");
		}
		if(cust3!=null) {
			throw new InvalidUserCredentialsException("User already exists by this phone number");
		}
		customer.setRole("USER");
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		return authRepo.save(customer);
	}

	@Override
	public String generateToken(String username, String role) {
		return jwtService.generateToken(username, role);
	}

	@Override
	public boolean validateToken(String token) {
		return jwtService.validateToken(token);
	}

	@Override
	public Customer loadByUsername(String username) {
		Optional<Customer> opt = authRepo.findByUsername(username);
		return opt.get();
	}

}
