package com.room.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.room.exception.InvalidRoomException;
import com.room.exception.InvalidRoomTypeException;
import com.room.exception.RoomAlreadyExistException;
import com.room.model.Room_Model;

@Service
public interface Room_Service {
	
	public List<Room_Model> getAll();
	public Room_Model getByRoomType(String roomType) throws InvalidRoomException;
	public Room_Model addRoom(Room_Model room) throws RoomAlreadyExistException;
	public String deleteRoom(String roomType) throws InvalidRoomTypeException;
	public Room_Model updateRoomCharge(String roomType,int value) throws InvalidRoomException;
	public Room_Model updateNoOfRooms(String roomType,int value) throws InvalidRoomException;

}
