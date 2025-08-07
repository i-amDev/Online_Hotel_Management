package com.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.dto.AuthRequest;
import com.security.exception.InvalidUserCredentialsException;
import com.security.model.Customer;
import com.security.repository.AuthRepo;
import com.security.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AuthRepo authRepo;

	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@Valid @RequestBody Customer customer) throws InvalidUserCredentialsException {
		authService.addUser(customer);
		String temp = "User added successfully";
		return new ResponseEntity<String>(temp, HttpStatus.OK);
	}

	@PostMapping("/generateToken")
	public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if(authentication.isAuthenticated()) {
			Customer customer = authRepo.findByUsername(authRequest.getUsername()).orElseThrow();
			return new ResponseEntity<String>(authService.generateToken(authRequest.getUsername(), customer.getRole()), HttpStatus.OK);
		}
		else {
			throw new RuntimeException("User is undefined");
		}
	}
	
	@GetMapping("/validateToken/{token}")
	public boolean validateToken(@PathVariable("token") String token) {
		return authService.validateToken(token);
	}
	
	@GetMapping("/getUserDetails/{username}")
	public ResponseEntity<Customer> loadByUsername(@PathVariable String username) {
		return new ResponseEntity<Customer>(authService.loadByUsername(username), HttpStatus.OK);
	}
	
	

}
