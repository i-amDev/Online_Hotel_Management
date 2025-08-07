package com.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.config.CustomUserDetails;
import com.security.model.Customer;
import com.security.repository.AuthRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private AuthRepo authRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Customer> customer = authRepo.findByUsername(username);
		Customer temp = customer.orElseThrow( () -> new UsernameNotFoundException("Username not found!!"));
		return new CustomUserDetails(temp);
	}

}
