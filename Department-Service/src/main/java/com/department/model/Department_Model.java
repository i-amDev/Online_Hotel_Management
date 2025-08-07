package com.department.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "department_collection")
@AllArgsConstructor
@NoArgsConstructor
public class Department_Model {
	
	@Id
	private long departmentId;
	@NotBlank(message = "Department name should not be empty")
	private String departmentName;
	@NotBlank(message = "Department description should not be empty")
	private String departmentDescription;
	@Min(value = 1, message = "No of employees should be greater than or equal to 1")
	private int noOfEmployees;

}
