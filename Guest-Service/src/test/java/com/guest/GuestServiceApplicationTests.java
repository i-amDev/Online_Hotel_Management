package com.guest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.guest.controller.Guest_Controller;
import com.guest.exception.GlobalGuestExceptionHandler;
import com.guest.exception.GuestAlreadyExistException;
import com.guest.exception.GuestNotFoundException;
import com.guest.model.Guest_Model;
import com.guest.service.Guest_ServiceImpl;

@SpringBootTest
class GuestServiceApplicationTests {

	@Autowired
	private Guest_Controller guest_Controller;

	@Autowired
	private Guest_ServiceImpl guest_ServiceImpl;
	
	@Autowired
	private GlobalGuestExceptionHandler globalGuestExceptionHandler;

	@Test
	void contextLoads() {
	}

	Guest_Model guest_Model = new Guest_Model("101", "Dev", 1234567890, "dev@gmail.com", "Male", "Bangalore");
	
	@Test
	public void toStringTest() {
		String testUser = guest_Model.toString();
		assertEquals(testUser, guest_Model.toString());
	}
	
	@Test
	public void hashTest() {
		Guest_Model guest_Model11 = new Guest_Model("101", "Dev", 1234567890, "dev@gmail.com", "Male", "Bangalore");
		Guest_Model guest_Model12 = new Guest_Model("101", "Dev", 1234567890, "dev@gmail.com", "Male", "Bangalore");
		assertEquals(guest_Model11.hashCode(), guest_Model12.hashCode());
	}


	@Test
	public void GetterAndSettersTest() throws GuestAlreadyExistException {
		Guest_Model temp = new Guest_Model();
		temp.setGuestId("101");
		temp.setName("Dev");
		temp.setPhoneNumber(1234567891);
		temp.setEmail("devs@gmail.com");
		temp.setGender("Male");
		temp.setAddress("Bangalore");
		Guest_Model test = guest_Controller.addGuest(temp).getBody();
		assertEquals(test.getGuestId(), temp.getGuestId());
		assertEquals(test.getName(), temp.getName());
		assertEquals(test.getPhoneNumber(), temp.getPhoneNumber());
		assertEquals(test.getEmail(), temp.getEmail());
		assertEquals(test.getGender(), temp.getGender());
		assertEquals(test.getAddress(), temp.getAddress());
		guest_Controller.deleteGuest(1234567891);
	}

	@Test
	public void getAllTest() {
		List<Guest_Model> test = guest_Controller.getAll().getBody();
		List<Guest_Model> temp = guest_ServiceImpl.getAll();
		assertEquals(test, temp);
	}

	@Test
	public void getByIdTest() throws GuestNotFoundException, GuestAlreadyExistException {
		Guest_Model guest_Model = new Guest_Model("101", "Dev", 1234567894, "dev34@gmail.com", "Male", "Bangalore");
		guest_Controller.addGuest(guest_Model);
		Guest_Model test = guest_Controller.getByPhoneNumber(1234567894).getBody();
		Guest_Model temp = guest_ServiceImpl.getByPhoneNumber(1234567894);
		assertEquals(test, temp);
		guest_Controller.deleteGuest(1234567894);
	}

	@Test
	public void deleteGuestTest() throws GuestAlreadyExistException {
		Guest_Model guest_Model = new Guest_Model("101", "Dev", 1234567891, "dev1@gmail.com", "Male", "Bangalore");
		guest_Controller.addGuest(guest_Model);
		String test = guest_Controller.deleteGuest(1234567891).getBody();
		Guest_Model guest_Model1 = new Guest_Model("101", "Dev", 1234567891, "dev1@gmail.com", "Male", "Bangalore");
		guest_Controller.addGuest(guest_Model1);
		String temp = guest_ServiceImpl.deleteGuest(1234567891);
		assertEquals(test, temp);
	}

	@Test
	public void updateNameTest() throws GuestAlreadyExistException, GuestNotFoundException {
		Guest_Model guest_Model = new Guest_Model("200", "Dev", 1234567891, "dev1@gmail.com", "Male", "Bangalore");
		guest_Controller.addGuest(guest_Model);
		Guest_Model test = guest_Controller.updateName(1234567891, "Dev").getBody();		
		Guest_Model temp = guest_ServiceImpl.updateName(1234567891, "Dev");
		assertEquals(test, temp);
		guest_Controller.deleteGuest(1234567891);
	}

	@Test
	public void updateEmailTest() throws GuestAlreadyExistException, GuestNotFoundException {
		Guest_Model guest_Model = new Guest_Model("200", "Dev", 1234567891, "dev1@gmail.com", "Male", "Bangalore");
		guest_Controller.addGuest(guest_Model);
		Guest_Model test = guest_Controller.updateEmail(1234567891, "dev@gmail.com").getBody();
		Guest_Model temp = guest_ServiceImpl.updateEmail(1234567891, "dev@gmail.com");
		assertEquals(test, temp);
		guest_Controller.deleteGuest(1234567891);
	}

	@Test
	public void GlobalExceptionHandlerTest() {
		GuestNotFoundException ex = assertThrows(GuestNotFoundException.class, () -> {
			throw new GuestNotFoundException("Global Exception Test");
		});
		ResponseEntity<String> response = globalGuestExceptionHandler.handleGuestIdNotFoundException(ex);

		String actual = "Global Exception Test";
		String expected = response.getBody();
		assertEquals(actual, expected);
	}
	
	@Test
	public void GlobalExceptionHandlerTest2() {
		GuestAlreadyExistException ex = assertThrows(GuestAlreadyExistException.class, () -> {
			throw new GuestAlreadyExistException("Global Exception Test");
		});
		ResponseEntity<String> response = globalGuestExceptionHandler.handleGuestAlreadyExistException(ex);

		String actual = "Global Exception Test";
		String expected = response.getBody();
		assertEquals(actual, expected);
	}
}
