package com.room.controller;

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
import com.room.exception.InvalidRoomException;
import com.room.exception.InvalidRoomTypeException;
import com.room.exception.RoomAlreadyExistException;
import com.room.model.Room_Model;
import com.room.service.Room_Service;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/room")
public class Room_Controller {
	
	@Autowired
	private Room_Service room_Service;
	
	/**
	 * This method is having a return type of ResponseEntity<List<Room_Model>>
	 * which stores the list of all room details after fetching the data from
	 * the database using Get mapping.
	 */
	
	@GetMapping("/getall")
	public ResponseEntity<List<Room_Model>> getAll() {
		return new ResponseEntity<List<Room_Model>>(room_Service.getAll(), HttpStatus.OK);
	}
	
	/**
	 * This method is having a return type of ResponseEntity<Room_Model> and
	 * it takes a String roomType as a parameter which stores Room Model object
	 * after fetching the data according to the given parameter from the database
	 * using Get mapping.
	 */
	
	@GetMapping("/{roomType}")
	public ResponseEntity<Room_Model> getByRoomType(@PathVariable("roomType") String roomType) throws InvalidRoomException {
		return new ResponseEntity<Room_Model>(room_Service.getByRoomType(roomType), HttpStatus.OK);
	}
	
	/**
	 * This method is having a return type of ResponseEntity<Room_Model> and
	 * it takes a Room Model as a parameter which adds Room Model object
	 * into the database using Post mapping.
	 */
	
	@PostMapping("/addroom")
	public ResponseEntity<Room_Model> addRoom(@Valid @RequestBody Room_Model room) throws RoomAlreadyExistException {
		return new ResponseEntity<Room_Model>(room_Service.addRoom(room), HttpStatus.OK);
	}
	
	/**
	 * This method is having a return type of ResponseEntity<String> and it takes a
	 * String roomType as a parameter and it stores the message after deleting the data
	 * according to the given parameter from the database using Delete mapping.
	 */
	
	@DeleteMapping("/delete/{roomType}")
	public ResponseEntity<String> deleteRoom(@PathVariable("roomType") String roomType) throws InvalidRoomTypeException {
		return new ResponseEntity<String>(room_Service.deleteRoom(roomType), HttpStatus.OK);
	}
	
	/**
	 * This method is having a return type of ResponseEntity<Room_Model> and
	 * it takes a String roomType and integer value as a parameter which stores Room
	 * Model object after updating the room charge according to the given
	 * parameter in the database using Put mapping.
	 */
	
	@PutMapping("/updateRoomCharge/{roomType}/{value}")
	public ResponseEntity<Room_Model> updateRoomCharge(@PathVariable("roomType") String roomType,@PathVariable("value") int value) throws InvalidRoomException {
		return new ResponseEntity<Room_Model>(room_Service.updateRoomCharge(roomType, value), HttpStatus.OK);
	}
	
	/**
	 * This method is having a return type of ResponseEntity<Room_Model> and
	 * it takes a String roomType and integer value as a parameter which
	 * stores Room Model object after updating the no of rooms according
	 * to the given value in the database using Put mapping.
	 */
	
	@PutMapping("/updateNoOfRooms/{roomType}/{value}")
	public ResponseEntity<Room_Model> updateNoOfRooms(@PathVariable("roomType") String roomType,@PathVariable("value") int value) throws InvalidRoomException {
		return new ResponseEntity<Room_Model>(room_Service.updateNoOfRooms(roomType, value), HttpStatus.OK);
	}
	

}
