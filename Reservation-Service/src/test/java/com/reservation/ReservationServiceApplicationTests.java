package com.reservation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.reservation.controller.Reservation_Controller;
import com.reservation.exception.GlobalReservationExceptionHandler;
import com.reservation.exception.ReservationAlreadyExistException;
import com.reservation.exception.ReservationNotFoundException;
import com.reservation.model.Reservation_Model;
import com.reservation.model.Room_Model;
import com.reservation.service.Reservation_ServiceImpl;

@SpringBootTest
class ReservationServiceApplicationTests {
	
	@Autowired
	private Reservation_Controller reservation_Controller;
	
	@Autowired
	private Reservation_ServiceImpl reservation_ServiceImpl;
	
	@Autowired
	private GlobalReservationExceptionHandler globalReservationExceptionHandler;

	@Test
	void contextLoads() {
	}
	
	Reservation_Model reservation_Model = new Reservation_Model("101", "Dev",22, "Bangalore", 1234567891, 5, 2, "Single", new Date(), new Date(), 2,"type",0);
	Room_Model room_Model = new Room_Model("R-01",2000,"Single Bed",5,true);
	
	@Test
	public void toStringTest() {
		String test = reservation_Model.toString();
		assertEquals(test, reservation_Model.toString());
		String test2 = room_Model.toString();
		assertEquals(test2, room_Model.toString());
	}
	
	@Test
	public void hashCodeTest() {
		Reservation_Model reservation_Model1 = new Reservation_Model("101", "Dev",22, "Bangalore", 1234567892, 5, 2, "Single", new Date(), new Date(), 2,"type",0);
		Reservation_Model reservation_Model2 = new Reservation_Model("101", "Dev",22, "Bangalore", 1234567892, 5, 2, "Single", new Date(), new Date(), 2,"type",0);
		assertEquals(reservation_Model1.hashCode(), reservation_Model2.hashCode());
		Room_Model room_Model1 = new Room_Model("R-01",2000,"Single Bed",5,true);
		Room_Model room_Model2 = new Room_Model("R-01",2000,"Single Bed",5,true);
		assertEquals(room_Model1.hashCode(), room_Model2.hashCode());
	}
	
	@Test
	public void GetterAndSettersTest() throws ReservationAlreadyExistException {
		Reservation_Model temp = new Reservation_Model();
		temp.setBookingId("101");
		temp.setName("Dev");
		temp.setAge(22);
		temp.setAddress("Bangalore");
		temp.setPhoneNumber(1234567812);
		temp.setNoOfGuests(4);
		temp.setNoOfRooms(2);
		temp.setStatus("Single");
		temp.setCheckInDate(new Date());
		temp.setCheckOutDate(new Date());
		temp.setNoOfNights(4);
		Reservation_Model test = reservation_Controller.addReservation(temp).getBody();
		assertEquals(test.getBookingId(), temp.getBookingId());
		assertEquals(test.getName(), temp.getName());
		assertEquals(test.getAge(), temp.getAge());
		assertEquals(test.getAddress(), temp.getAddress());
		assertEquals(test.getPhoneNumber(), temp.getPhoneNumber());
		assertEquals(test.getNoOfGuests(), temp.getNoOfGuests());
		assertEquals(test.getNoOfRooms(), temp.getNoOfRooms());
		assertEquals(test.getStatus(), temp.getStatus());
		assertEquals(test.getCheckInDate(), temp.getCheckInDate());
		assertEquals(test.getCheckOutDate(), temp.getCheckOutDate());
		assertEquals(test.getNoOfNights(), temp.getNoOfNights());
		reservation_Controller.deleteReservation(1234567812);
	}
	
	@Test
	public void getAllTest() {
		List<Reservation_Model> test = reservation_Controller.getAll().getBody();
		List<Reservation_Model> temp = reservation_ServiceImpl.getAll();
		assertEquals(test, temp);
	}

	
	@Test
	public void getByIdTest() throws ReservationNotFoundException, ReservationAlreadyExistException {
		Reservation_Model reservation_Model = new Reservation_Model("101", "a",22, "b", 1232267892, 5, 2, "Single", new Date(), new Date(), 2,"type",0);
		reservation_Controller.addReservation(reservation_Model);
		Reservation_Model test = reservation_Controller.getByPhoneNumber(1232267892).getBody();
		Reservation_Model temp = reservation_ServiceImpl.getByPhoneNumber(1232267892);
		assertEquals(test, temp);
		reservation_Controller.deleteReservation(1232267892);
	}

	@Test
	public void deleteGuestTest() throws ReservationAlreadyExistException {
		Reservation_Model reservation_Model = new Reservation_Model("101", "a",22, "b", 1232267892, 5, 2, "Single", new Date(), new Date(), 2,"type",0);
		reservation_Controller.addReservation(reservation_Model);
		String test = reservation_Controller.deleteReservation(1232267892).getBody();
		Reservation_Model reservation_Model2 = new Reservation_Model("101", "a",22, "b", 1232267892, 5, 2, "Single", new Date(), new Date(), 2,"type",0);
		reservation_Controller.addReservation(reservation_Model2);
		String temp = reservation_ServiceImpl.deleteReservation(1232267892);
		assertEquals(test, temp);
	}

	@Test
	public void updateAddressTest() throws ReservationAlreadyExistException {
		Reservation_Model reservation_Model = new Reservation_Model("101", "a",22, "b", 1232267892, 5, 2, "Single", new Date(), new Date(), 2,"type",0);
		reservation_Controller.addReservation(reservation_Model);
		Reservation_Model test = reservation_Controller.updateAddress(1232267892, "Chennai").getBody();
		Reservation_Model temp = reservation_ServiceImpl.updateAddress(1232267892, "Chennai");
		assertEquals(test, temp);
		reservation_Controller.deleteReservation(1232267892);
	}

	@Test
	public void updateNameTest() throws ReservationAlreadyExistException {
		Reservation_Model reservation_Model = new Reservation_Model("101", "a",22, "b", 1232267892, 5, 2, "Single", new Date(), new Date(), 2,"type",0);
		reservation_Controller.addReservation(reservation_Model);
		Reservation_Model test = reservation_Controller.updateName(1232267892, "Dev").getBody();
		Reservation_Model temp = reservation_ServiceImpl.updateName(1232267892, "Dev");
		assertEquals(test, temp);
		reservation_Controller.deleteReservation(1232267892);
	}

	@Test
	public void GlobalExceptionHandlerTest() {
		ReservationNotFoundException ex = assertThrows(ReservationNotFoundException.class, () -> {
			throw new ReservationNotFoundException("Global Exception Test");
		});
		ResponseEntity<String> response = globalReservationExceptionHandler.handleGuestNotFoundException(ex);

		String actual = "Global Exception Test";
		String expected = response.getBody();
		assertEquals(actual, expected);
	}
	
	@Test
	public void GlobalExceptionHandlerTest2() {
		ReservationAlreadyExistException ex = assertThrows(ReservationAlreadyExistException.class, () -> {
			throw new ReservationAlreadyExistException("Global Exception Test");
		});
		ResponseEntity<String> response = globalReservationExceptionHandler.handleReservationAlreadyExistException(ex);

		String actual = "Global Exception Test";
		String expected = response.getBody();
		assertEquals(actual, expected);
	}
	
	

}
