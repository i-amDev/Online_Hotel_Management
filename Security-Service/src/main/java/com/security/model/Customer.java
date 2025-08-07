package com.security.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Document(collection = "customer_collection")
public class Customer{
	
	@Id
	private String customerId;
	@NotBlank(message = "Username should not be blank")
	private String username;
	@NotBlank(message = "Password should not be blank")
	private String password;
	@NotBlank(message = "Email should not be blank")
	private String email;
	@NotBlank(message = "Address should not be blank")
	private String address;
	@Min(value = 1000000000,message = "Enter valid phone Number")
	@Max(value = 9999999999L,message = "Enter valid phone Number")
	private long phoneNumber;
	private String role;
}
