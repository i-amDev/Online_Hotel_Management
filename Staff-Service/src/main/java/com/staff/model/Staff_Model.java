package com.staff.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "staff_collection")
@AllArgsConstructor
@NoArgsConstructor
public class Staff_Model {
	
	@Id
	private String staffId;
	@NotBlank(message = "Name should not be empty")
	private String name;
	@NotBlank(message = "Gender should not be empty")
	private String gender;
	@NotBlank(message = "Email should not be empty")
	private String email;
	@NotBlank(message = "Address should not be empty")
	private String address;
	@NotBlank(message = "Staff post should not be empty")
	private String staffPost;
	@Min(value = 1,message = "Salary should be greater than 0")
	private int salary;
	

}
