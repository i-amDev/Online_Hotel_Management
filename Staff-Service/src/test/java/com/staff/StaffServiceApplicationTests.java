package com.staff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.staff.controller.Staff_Controller;
import com.staff.exception.GlobalStaffExceptionHandler;
import com.staff.exception.InvalidStaffException;
import com.staff.exception.StaffAlreadyExistException;
import com.staff.model.Staff_Model;
import com.staff.service.Staff_ServiceImpl;

@SpringBootTest
class StaffServiceApplicationTests {
	
	@Autowired
	private Staff_Controller staff_Controller;
	
	@Autowired
	private Staff_ServiceImpl staff_ServiceImpl;
	
	@Autowired
	private GlobalStaffExceptionHandler globalStaffExceptionHandler;

	@Test
	void contextLoads() {
	}
	
	Staff_Model staff_Model = new Staff_Model("101", "Alex", "Male", "alex@gmail.com", "Pune", "Designer", 15000);
	
	@Test
	public void GetterAndSettersTest() throws InvalidStaffException, StaffAlreadyExistException {
		Staff_Model test = new Staff_Model();
		test.setStaffId("101");
		test.setName("Alex");
		test.setGender("Male");
		test.setEmail("alexa@gmail.com");
		test.setAddress("Pune");
		test.setStaffPost("Designer");
		test.setSalary(15000);
		Staff_Model temp = staff_Controller.addUser(test).getBody();
		assertEquals(test.getStaffId(), temp.getStaffId());
		assertEquals(test.getName(), temp.getName());
		assertEquals(test.getGender(), temp.getGender());
		assertEquals(test.getEmail(), temp.getEmail());
		assertEquals(test.getAddress(), temp.getAddress());
		assertEquals(test.getStaffPost(), temp.getStaffPost());
		assertEquals(test.getSalary(), temp.getSalary());
		staff_Controller.deleteUser("alexa@gmail.com");
	}
	
	@Test
	public void toStringTest() {
		String test = staff_Model.toString();
		assertEquals(test, staff_Model.toString());
	}
	
	@Test
	public void hashcodeTest() {
		Staff_Model staff_Model1 = new Staff_Model("101", "Alex", "Male", "alex1@gmail.com", "Pune", "Designer", 15000);
		Staff_Model staff_Model2 = new Staff_Model("101", "Alex", "Male", "alex1@gmail.com", "Pune", "Designer", 15000);
		assertEquals(staff_Model1.hashCode(), staff_Model2.hashCode());
	}
	
	@Test
	public void getAllTest() {
		List<Staff_Model> test = staff_Controller.getAll().getBody();
		List<Staff_Model> temp = staff_ServiceImpl.getAll();
		assertEquals(test, temp);
	}
	
	@Test
	public void getByIdTest() throws InvalidStaffException, StaffAlreadyExistException {
		Staff_Model staff_Model1 = new Staff_Model("101", "Alex", "Male", "alex1@gmail.com", "Pune", "Designer", 15000);
		staff_Controller.addUser(staff_Model1);
		Staff_Model test = staff_Controller.getByEmail("alex1@gmail.com").getBody();
		Staff_Model temp = staff_ServiceImpl.getByEmail("alex1@gmail.com");
		assertEquals(test, temp);
		staff_Controller.deleteUser("alex1@gmail.com");
	}
	
	@Test
	public void deleteTest() throws InvalidStaffException, StaffAlreadyExistException {
		Staff_Model staff_Model1 = new Staff_Model("101", "Alex", "Male", "alex1@gmail.com", "Pune", "Designer", 15000);
		staff_Controller.addUser(staff_Model1);
		String test = staff_Controller.deleteUser("alex1@gmail.com").getBody();
		Staff_Model staff_Model12 = new Staff_Model("101", "Alex", "Male", "alex1@gmail.com", "Pune", "Designer", 15000);
		staff_Controller.addUser(staff_Model12);
		String temp = staff_ServiceImpl.deleteStaff("alex1@gmail.com");
		assertEquals(test, temp);
	}
	
	@Test
	public void updateSalaryTest() throws InvalidStaffException, StaffAlreadyExistException {
		Staff_Model staff_Model1 = new Staff_Model("101", "Alex", "Male", "alex1@gmail.com", "Pune", "Designer", 15000);
		staff_Controller.addUser(staff_Model1);
		Staff_Model test = staff_Controller.updateSalary("alex1@gmail.com",16000).getBody();
		Staff_Model temp = staff_ServiceImpl.updateSalary("alex1@gmail.com",16000);
		assertEquals(test, temp);
		staff_Controller.deleteUser("alex1@gmail.com");
	}
	
	@Test
	public void updateNameTest() throws InvalidStaffException, StaffAlreadyExistException {
		Staff_Model staff_Model1 = new Staff_Model("101", "Alex", "Male", "alex1@gmail.com", "Pune", "Designer", 15000);
		staff_Controller.addUser(staff_Model1);
		Staff_Model test = staff_Controller.updateName("alex1@gmail.com","Bob").getBody();		
		Staff_Model temp = staff_ServiceImpl.updateName("alex1@gmail.com","Bob");
		assertEquals(test, temp);
		staff_Controller.deleteUser("alex1@gmail.com");
	}
	
	@Test
	public void updateAddressTest() throws InvalidStaffException, StaffAlreadyExistException {
		Staff_Model staff_Model1 = new Staff_Model("101", "Alex", "Male", "alex1@gmail.com", "Pune", "Designer", 15000);
		staff_Controller.addUser(staff_Model1);
		Staff_Model test = staff_Controller.updateAddress("alex1@gmail.com","Pune").getBody();		
		Staff_Model temp = staff_ServiceImpl.updateAddress("alex1@gmail.com","Pune");
		assertEquals(test, temp);
		staff_Controller.deleteUser("alex1@gmail.com");
	}
	
	@Test
	public void GlobalExceptionTest() {
		InvalidStaffException ex = assertThrows(InvalidStaffException.class,() -> {
			throw new InvalidStaffException("Global Exception Test");
		});
		ResponseEntity<String> response = globalStaffExceptionHandler.handlerInvalidStaffIdException(ex);
		String actual = "Global Exception Test";
		String expected = response.getBody();
		assertEquals(actual, expected);
	}
	
	@Test
	public void GlobalExceptionTest2() {
		StaffAlreadyExistException ex = assertThrows(StaffAlreadyExistException.class,() -> {
			throw new StaffAlreadyExistException("Global Exception Test");
		});
		ResponseEntity<String> response = globalStaffExceptionHandler.handlerStaffAlreadyExistException(ex);
		String actual = "Global Exception Test";
		String expected = response.getBody();
		assertEquals(actual, expected);
	}
	
	

}
