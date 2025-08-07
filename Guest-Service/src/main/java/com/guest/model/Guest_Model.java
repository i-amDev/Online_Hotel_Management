package com.guest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "guest_collection")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guest_Model {
	
	@Id
	private String guestId;
	@NotBlank(message = "Name should not be blank")
	private String name;
	@Min(value = 1000000000, message = "Phone number length should be equal to 10")
	@Max(value = 9999999999L, message = "Phone number length should be equal to 10")
	private long phoneNumber;
	@NotBlank(message = "Email length should not be blank")
	private String email;
	@NotBlank(message = "Gender should not be blank")
	private String gender;
	@NotBlank(message = "Address should not be blank")
	private String address;
}
