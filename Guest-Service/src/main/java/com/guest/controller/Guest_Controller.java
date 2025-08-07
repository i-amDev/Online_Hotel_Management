package com.guest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guest.exception.GuestAlreadyExistException;
import com.guest.exception.GuestNotFoundException;
import com.guest.model.Guest_Model;
import com.guest.service.Guest_Service;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/guest")
//@CrossOrigin("http://localhost:4200")
public class Guest_Controller {

	@Autowired
	private Guest_Service guest_Service;
	
	/**
	 * This method is having a return type of ResponseEntity<List<Guest_Model>>
	 * which stores the list of all guest details after fetching the data from
	 * the database using Get mapping.
	 */

	@GetMapping("/getall")
	public ResponseEntity<List<Guest_Model>> getAll() {
		return new ResponseEntity<List<Guest_Model>>(guest_Service.getAll(), HttpStatus.OK);
	}
	
	/**
	 * This method is having a return type of ResponseEntity<Guest_Model> and
	 * it takes a long phoneNumber as a parameter which stores Guest Model object
	 * after fetching the data according to the given parameter from the database
	 * using Get mapping.
	 */

	@GetMapping("/{phoneNumber}")
	public ResponseEntity<Guest_Model> getByPhoneNumber(@PathVariable("phoneNumber") long phoneNumber) throws GuestNotFoundException {
		return new ResponseEntity<Guest_Model>(guest_Service.getByPhoneNumber(phoneNumber), HttpStatus.OK);
	}
	
	/**
	 * This method is having a return type of ResponseEntity<Guest_Model> and
	 * it takes a Guest Model as a parameter which adds Guest Model object
	 * into the database using Post mapping.
	 */

	@PostMapping("/addguest")
	public ResponseEntity<Guest_Model> addGuest(@Valid @RequestBody Guest_Model user) throws GuestAlreadyExistException {
		return new ResponseEntity<Guest_Model>(guest_Service.addGuest(user), HttpStatus.OK);
	}
	
	/**
	 * This method is having a return type of ResponseEntity<String> and it takes a
	 * long phoneNumber as a parameter and it stores the message after deleting the data
	 * according to the given parameter from the database using Delete mapping.
	 */

	@DeleteMapping("/delete/{phoneNumber}")
	public ResponseEntity<String> deleteGuest(@PathVariable("phoneNumber") long phoneNumber) {
		return new ResponseEntity<String>(guest_Service.deleteGuest(phoneNumber), HttpStatus.OK);
	}
	
	/**
	 * This method is having a return type of ResponseEntity<Guest_Model> and
	 * it takes a long phoneNumber and String value as a parameter which stores Guest
	 * Model object after updating the name of the guest according to the given
	 * parameter in the database using Put mapping.
	 */

	@PutMapping("/updateName/{phoneNumber}/{value}")
	public ResponseEntity<Guest_Model> updateName(@PathVariable("phoneNumber") long phoneNumber, @PathVariable("value") String value) throws GuestNotFoundException {
		return new ResponseEntity<Guest_Model>(guest_Service.updateName(phoneNumber, value), HttpStatus.OK);
	}
	
	/**
	 * This method is having a return type of ResponseEntity<Guest_Model> and
	 * it takes a long phoneNumber and String value as a parameter which
	 * stores Department Model object after updating the email of the guest according
	 * to the given value in the database using Put mapping.
	 */

	@PutMapping("/updateEmail/{phoneNumber}/{value}")
	public ResponseEntity<Guest_Model> updateEmail(@PathVariable("phoneNumber") long phoneNumber,@PathVariable("value") String value) throws GuestNotFoundException {
		return new ResponseEntity<Guest_Model>(guest_Service.updateEmail(phoneNumber,value), HttpStatus.OK);
	}

}
