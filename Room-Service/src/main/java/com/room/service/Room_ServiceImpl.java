package com.room.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.room.exception.InvalidRoomException;
import com.room.exception.InvalidRoomTypeException;
import com.room.exception.RoomAlreadyExistException;
import com.room.model.Room_Model;
import com.room.repository.Room_Repo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Room_ServiceImpl implements Room_Service {

	@Autowired
	private Room_Repo room_Repo;
	
	/**
	 * This method is having a return type of List<Room_Model>
	 * which stores the list of all room details after fetching the data from
	 * the database using Repository.
	 */

	@Override
	public List<Room_Model> getAll() {
		List<Room_Model> list = room_Repo.findAll();
		if (list.isEmpty()) {
			log.error("List is Empty!! Please add some data");
		} else {
			log.info("List of room is fetched!!");
		}
		return list;
	}
	
	/**
	 * This method is having a return type of Room_Model and
	 * it takes a String roomType as a parameter which stores Room Model object
	 * after fetching the data according to the given parameter from the database
	 * using Repository.
	 */

	@Override
	public Room_Model getByRoomType(String roomType) throws InvalidRoomException {
		Room_Model temp = room_Repo.findByRoomType(roomType);
		if (temp == null) {
			log.error("No data is present for the roomType " + roomType);
			throw new InvalidRoomException("No room found for the given roomType!!");
		} else {
			log.info("Data is fetched for the roomType " + roomType);
		}
		return temp;
	}
	
	/**
	 * This method is having a return type of Room_Model and
	 * it takes a Room Model as a parameter which adds Room Model object
	 * into the database using Repository.
	 */

	@Override
	public Room_Model addRoom(Room_Model room) throws RoomAlreadyExistException {
		Room_Model temp = room_Repo.findByRoomType(room.getRoomType());
		if(temp!=null) {
			log.error("Room with the given room type already exists");
			throw new RoomAlreadyExistException("Room with the given room type already exists");
		}
		log.info("Room added successfully");
		return room_Repo.save(room);
	}
	
	/**
	 * This method is having a return type of String and it takes a
	 * String roomType as a parameter and it stores the message after deleting the data
	 * according to the given parameter from the database using Repository.
	 */

	@Override
	public String deleteRoom(String roomType) throws InvalidRoomTypeException {
		Room_Model temp = room_Repo.findByRoomType(roomType);
		if(temp==null) {
			log.error("Invalid roomType. Please provide correct room roomType");
			throw new InvalidRoomTypeException("Invalid Id. Please provide correct room Id");
		}
		room_Repo.deleteByRoomType(roomType);
		log.info("Room with roomType " + roomType + " deleted successfully!");
		return "Room with roomType " + roomType + " has been deleted successfully";
	}
	
	/**
	 * This method is having a return type of Room_Model and
	 * it takes a String roomType and integer value as a parameter which stores Room
	 * Model object after updating the room charge according to the given
	 * parameter in the database using Repository.
	 */

	@Override
	public Room_Model updateRoomCharge(String roomType, int value) throws InvalidRoomException {
		Room_Model temp = room_Repo.findByRoomType(roomType);
		if (temp == null) {
			throw new InvalidRoomException(
					"Can't update the room charge for the unknown roomType. Please provide a correct roomType");
		}
		temp.setRoomCharge(value);
		log.info("Room Charge updated successfully!!");
		return room_Repo.save(temp);
	}
	
	/**
	 * This method is having a return type of Room_Model and
	 * it takes a String roomType and integer value as a parameter which
	 * stores Room Model object after updating the no of rooms according
	 * to the given value in the database using Repository.
	 */

	@Override
	public Room_Model updateNoOfRooms(String roomType, int value) throws InvalidRoomException {
		Room_Model temp = room_Repo.findByRoomType(roomType);
		if (temp == null) {
			throw new InvalidRoomException(
					"Can't update no of Rooms for the unknown roomType. Please provide a correct roomType");
		}
		temp.setNoOfRooms(value);
		log.info("No of rooms updated successfully!!");
		return room_Repo.save(temp);
	}
}