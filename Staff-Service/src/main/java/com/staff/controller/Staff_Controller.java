package com.staff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staff.exception.InvalidStaffException;
import com.staff.exception.StaffAlreadyExistException;
import com.staff.model.Staff_Model;
import com.staff.service.Staff_Service;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/staff")
public class Staff_Controller {

	@Autowired
	private Staff_Service staff_Service;
	
	/**
	 * This method is having a return type of ResponseEntity<List<Staff_Model>>
	 * which stores the list of all staff details after fetching the data from
	 * the database using Get mapping.
	 */
	
	@GetMapping("/getall")
	public ResponseEntity<List<Staff_Model>> getAll() {
		return new ResponseEntity<List<Staff_Model>>(staff_Service.getAll(), HttpStatus.OK);
	}
	
	/**
	 * This method is having a return type of ResponseEntity<Staff_Model> and
	 * it takes a String email as a parameter which stores Staff Model object
	 * after fetching the data according to the given parameter from the database
	 * using Get mapping.
	 */

	@GetMapping("/{email}")
	public ResponseEntity<Staff_Model> getByEmail(@PathVariable("email") String email) throws InvalidStaffException {
		return new ResponseEntity<Staff_Model>(staff_Service.getByEmail(email), HttpStatus.OK);
	}
	
	/**
	 * This method is having a return type of ResponseEntity<Staff_Model> and
	 * it takes a Staff Model as a parameter which adds Staff Model object
	 * into the database using Post mapping.
	 */

	@PostMapping("/addstaff")
	public ResponseEntity<Staff_Model> addUser(@Valid @RequestBody Staff_Model user) throws InvalidStaffException, StaffAlreadyExistException {
		return new ResponseEntity<Staff_Model>(staff_Service.addStaff(user), HttpStatus.OK);
	}
	
	/**
	 * This method is having a return type of ResponseEntity<String> and it takes a
	 * String email as a parameter and it stores the message after deleting the data
	 * according to the given parameter from the database using Delete mapping.
	 */

	@DeleteMapping("/delete/{email}")
	public ResponseEntity<String> deleteUser(@PathVariable("email") String email) throws InvalidStaffException {
		return new ResponseEntity<String>(staff_Service.deleteStaff(email), HttpStatus.OK);
	}
	
	/**
	 * This method is having a return type of ResponseEntity<Staff_Model> and
	 * it takes a String email and integer value as a parameter which stores Staff
	 * Model object after updating the salary of the staff according to the given
	 * parameter in the database using Put mapping.
	 */

	@PutMapping("/updateSalary/{email}/{value}")
	public ResponseEntity<Staff_Model> updateSalary(@PathVariable("email") String email, @PathVariable("value") int value) throws InvalidStaffException {
		return new ResponseEntity<Staff_Model>(staff_Service.updateSalary(email, value), HttpStatus.OK);
	}
	
	/**
	 * This method is having a return type of ResponseEntity<Staff_Model> and
	 * it takes a String email and String value as a parameter which stores Staff
	 * Model object after updating the name of the staff according to the given
	 * parameter in the database using Put mapping.
	 */
	
	@PutMapping("/updateName/{email}/{value}")
	public ResponseEntity<Staff_Model> updateName(@PathVariable("email") String email, @PathVariable("value") String value){
		return new ResponseEntity<Staff_Model>(staff_Service.updateName(email, value), HttpStatus.OK);
	}
	
	/**
	 * This method is having a return type of ResponseEntity<Staff_Model> and
	 * it takes a String email and String value as a parameter which stores Staff
	 * Model object after updating the address of the staff according to the given
	 * parameter in the database using Put mapping.
	 */
	
	@PutMapping("/updateAddress/{email}/{value}")
	public ResponseEntity<Staff_Model> updateAddress(@PathVariable("email") String email, @PathVariable("value") String value){
		return new ResponseEntity<Staff_Model>(staff_Service.updateAddress(email, value), HttpStatus.OK);
	}

}
