package com.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.room.controller.Room_Controller;
import com.room.exception.GlobalRoomExceptionHandler;
import com.room.exception.InvalidRoomException;
import com.room.exception.InvalidRoomTypeException;
import com.room.exception.RoomAlreadyExistException;
import com.room.model.Room_Model;
import com.room.service.Room_ServiceImpl;

@SpringBootTest
class RoomServiceApplicationTests {
	
	@Autowired
	private Room_Controller room_Controller;
	
	@Autowired
	private Room_ServiceImpl room_ServiceImpl;
	
	@Autowired
	private GlobalRoomExceptionHandler globalRoomExceptionHandler;

	@Test
	void contextLoads() {
	}
	
	Room_Model room_Model = new Room_Model("R-01", 2000, "aa", 5);
	
	@Test
	public void GettersAndSettersTest() throws RoomAlreadyExistException, InvalidRoomException, InvalidRoomTypeException {
		Room_Model test = new Room_Model();
		test.setRoomId("R-03");
		test.setRoomCharge(2000);
		test.setNoOfRooms(5);
		test.setRoomType("New");
		Room_Model temp = room_Controller.addRoom(test).getBody();
		assertEquals(test.getRoomType(), temp.getRoomType());
		assertEquals(test.getNoOfRooms(), temp.getNoOfRooms());
		assertEquals(test.getRoomCharge(), temp.getRoomCharge());
		assertEquals(test.getRoomId(), temp.getRoomId());
		room_Controller.deleteRoom("New");
	}
	
	@Test
	public void hashCodeTest() {
		Room_Model room_Model1 = new Room_Model("R-01", 2000, "Single Bed", 5);
		Room_Model room_Model2 = new Room_Model("R-01", 2000, "Single Bed", 5);
		assertEquals(room_Model1.hashCode(), room_Model2.hashCode());
	}
	
	@Test
	public void toStringTest() {
		String test = room_Model.toString();
		assertEquals(test, room_Model.toString());
	}
	
	@Test
	public void getAllTest() {
		List<Room_Model> test = room_Controller.getAll().getBody();
		List<Room_Model> temp = room_ServiceImpl.getAll();
		assertEquals(test, temp);
	}
	
	@Test
	public void getByIdTest() throws InvalidRoomException, RoomAlreadyExistException, InvalidRoomTypeException {
		Room_Model room_Model = new Room_Model("R-1", 4000, "abc", 15);
		room_Controller.addRoom(room_Model);
		Room_Model test = room_Controller.getByRoomType("abc").getBody();
		Room_Model temp = room_ServiceImpl.getByRoomType("abc");
		assertEquals(test, temp);
		room_Controller.deleteRoom("abc");
	}
	
	@Test
	public void deleteTest() throws InvalidRoomException, RoomAlreadyExistException, InvalidRoomTypeException {
		Room_Model room_Model = new Room_Model("R-1", 4000, "abc", 15);
		room_Controller.addRoom(room_Model);
		String test = room_Controller.deleteRoom("abc").getBody();
		Room_Model room_Model2 = new Room_Model("R-1", 4000, "abc", 15);
		room_Controller.addRoom(room_Model2);
		String temp = room_ServiceImpl.deleteRoom("abc");
		assertEquals(test, temp);
	}
	
	@Test
	public void updateRoomChargeTest() throws InvalidRoomException, RoomAlreadyExistException, InvalidRoomTypeException {
		Room_Model room_Model = new Room_Model("R-1", 4000, "abc", 15);
		room_Controller.addRoom(room_Model);
		Room_Model test = room_Controller.updateRoomCharge("abc",1500).getBody();
		Room_Model temp = room_ServiceImpl.updateRoomCharge("abc",1500);
		assertEquals(test, temp);
	}
	
	@Test
	public void updateNoOfRoomsByTypeTest() throws RoomAlreadyExistException, InvalidRoomTypeException, InvalidRoomException {
		Room_Model room_Model = new Room_Model("R-022", 4000, "New Type2", 15);
		room_Controller.addRoom(room_Model);
		Room_Model test = room_Controller.updateNoOfRooms("New Type2", 15).getBody();
		Room_Model temp = room_ServiceImpl.updateNoOfRooms("New Type2", 15);
		assertEquals(test, temp);
		room_Controller.deleteRoom("New Type2");
	}
	
	@Test
	public void updateNoOfRoomsTest() throws InvalidRoomException, RoomAlreadyExistException, InvalidRoomTypeException {
		Room_Model room_Model = new Room_Model("R-1", 4000, "aaa", 15);
		room_Controller.addRoom(room_Model);
		Room_Model test = room_Controller.updateNoOfRooms("aaa",6).getBody();
		Room_Model temp = room_ServiceImpl.updateNoOfRooms("aaa",6);
		assertEquals(test, temp);
		room_Controller.deleteRoom("aaa");
	}
	
	@Test
	public void GlobalExceptionTest() {
		InvalidRoomException ex = assertThrows(InvalidRoomException.class, () -> {
			throw new InvalidRoomException("Global Exception Test");
		});
		ResponseEntity<String> response = globalRoomExceptionHandler.handleInvalidRoomIdException(ex);
		
		String actual = "Global Exception Test";
		String expected = response.getBody();
		assertEquals(actual, expected);

	}
	
	@Test
	public void GlobalExceptionTest2() {
		RoomAlreadyExistException ex = assertThrows(RoomAlreadyExistException.class, () -> {
			throw new RoomAlreadyExistException("Global Exception Test");
		});
		ResponseEntity<String> response = globalRoomExceptionHandler.handleRoomAlreadyExistException(ex);
		
		String actual = "Global Exception Test";
		String expected = response.getBody();
		assertEquals(actual, expected);

	}
	
	@Test
	public void GlobalExceptionTest3() {
		InvalidRoomTypeException ex = assertThrows(InvalidRoomTypeException.class, () -> {
			throw new InvalidRoomTypeException("Global Exception Test");
		});
		ResponseEntity<String> response = globalRoomExceptionHandler.handleInvalidRoomTypeException(ex);
		
		String actual = "Global Exception Test";
		String expected = response.getBody();
		assertEquals(actual, expected);

	}
	
	
	

}
