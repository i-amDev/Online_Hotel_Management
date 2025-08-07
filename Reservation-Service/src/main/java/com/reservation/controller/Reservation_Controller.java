package com.reservation.controller;

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

import com.reservation.exception.ReservationAlreadyExistException;
import com.reservation.exception.ReservationNotFoundException;
import com.reservation.model.Reservation_Model;
import com.reservation.model.Room_Model;
import com.reservation.service.Reservation_Service;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reservation")
//@CrossOrigin("http://localhost:4200")
public class Reservation_Controller {

	@Autowired
	private Reservation_Service reservation_Service;
	
//	@GetMapping("/getroom")
//	public ResponseEntity<List<Room_Model>> getAllRoom() {
//		return new ResponseEntity<List<Room_Model>>(reservation_Service.getAllRoom(), HttpStatus.OK);
//	}
	
	/**
	 * This method is having a return type of ResponseEntity<List<Reservation_Model>>
	 * which stores the list of all reservation details after fetching the data from
	 * the database using Get mapping.
	 */

	@GetMapping("/getall")
	public ResponseEntity<List<Reservation_Model>> getAll() {
		return new ResponseEntity<List<Reservation_Model>>(reservation_Service.getAll(), HttpStatus.OK);
	}
	
	/**
	 * This method is having a return type of ResponseEntity<Reservation_Model> and
	 * it takes a long phoneNumber as a parameter which stores Reservation Model object
	 * after fetching the data according to the given parameter from the database
	 * using Get mapping.
	 */

	@GetMapping("/getByPhone/{phoneNumber}")
	public ResponseEntity<Reservation_Model> getByPhoneNumber(@PathVariable("phoneNumber") long phoneNumber) throws ReservationNotFoundException {
		return new ResponseEntity<Reservation_Model>(reservation_Service.getByPhoneNumber(phoneNumber), HttpStatus.OK);
	}
	
	/**
	 * This method is having a return type of ResponseEntity<Reservation_Model> and
	 * it takes a Reservation Model as a parameter which adds Reservation Model object
	 * into the database using Post mapping.
	 */

	@PostMapping("/addreservation")
	public ResponseEntity<Reservation_Model> addReservation(@Valid @RequestBody Reservation_Model reservation) throws ReservationAlreadyExistException {
		return new ResponseEntity<Reservation_Model>(reservation_Service.addReservation(reservation), HttpStatus.OK);
	}
	
	/**
	 * This method is having a return type of ResponseEntity<String> and it takes a
	 * long phoneNumber as a parameter and it stores the message after deleting the data
	 * according to the given parameter from the database using Delete mapping.
	 */

	@DeleteMapping("/delete/{phoneNumber}")
	public ResponseEntity<String> deleteReservation(@PathVariable("phoneNumber") long phoneNumber) {
		return new ResponseEntity<String>(reservation_Service.deleteReservation(phoneNumber), HttpStatus.OK);
	}

	/**
	 * This method is having a return type of ResponseEntity<Reservation_Model> and
	 * it takes a long phoneNumber and String value as a parameter which stores Reservation
	 * Model object after updating the name of the guest according to the given
	 * parameter in the database using Put mapping.
	 */
	
	@PutMapping("/updateName/{phoneNumber}/{value}")
	public ResponseEntity<Reservation_Model> updateName(@PathVariable("phoneNumber") long phoneNumber, @PathVariable("value") String value) {
		return new ResponseEntity<Reservation_Model>(reservation_Service.updateName(phoneNumber, value), HttpStatus.OK);
	}
	
	/**
	 * This method is having a return type of ResponseEntity<Guest_Model> and
	 * it takes a long phoneNumber and String value as a parameter which
	 * stores Reservation Model object after updating the address of the guest according
	 * to the given value in the database using Put mapping.
	 */
	
	@PutMapping("/updateAddress/{phoneNumber}/{value}")
	public ResponseEntity<Reservation_Model> updateAddress(@PathVariable("phoneNumber") long phoneNumber, @PathVariable("value") String value) {
		return new ResponseEntity<Reservation_Model>(reservation_Service.updateAddress(phoneNumber, value), HttpStatus.OK);
	}

}
