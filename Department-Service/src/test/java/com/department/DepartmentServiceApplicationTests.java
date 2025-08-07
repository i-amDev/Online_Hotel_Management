package com.department;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.department.controller.Department_Controller;
import com.department.exception.DepartmentAlreadyExistException;
import com.department.exception.DepartmentNameNotFoundException;
import com.department.exception.GlobalDepartmentExceptionHandler;
import com.department.model.Department_Model;
import com.department.service.Department_Service;

@SpringBootTest
class DepartmentServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private Department_Service department_Service;

	@Autowired
	private Department_Controller department_Controller;

	@Autowired
	private GlobalDepartmentExceptionHandler globalDepartmentExceptionHandler;

	Department_Model department_Model = new Department_Model(1, "Cook", "Has to cook food", 10);

	@Test
	public void GetterandSettersTest() throws DepartmentAlreadyExistException, DepartmentNameNotFoundException {
		Department_Model temp = new Department_Model();
		temp.setDepartmentId(1);
		temp.setDepartmentName("A");
		temp.setDepartmentDescription("Has to check wirings");
		temp.setNoOfEmployees(100);
		Department_Model test = department_Controller.addDepartment(temp).getBody();
		assertEquals(test.getDepartmentId(), temp.getDepartmentId());
		assertEquals(test.getDepartmentDescription(), temp.getDepartmentDescription());
		assertEquals(test.getDepartmentName(), temp.getDepartmentName());
		assertEquals(test.getNoOfEmployees(), temp.getNoOfEmployees());
		department_Controller.deleteDepartment("A");
	}

	@Test
	public void hashCodeTest() {
		Department_Model m1 = new Department_Model(1, "AA", "Has to cook food", 10);
		Department_Model m2 = new Department_Model(1, "AA", "Has to cook food", 10);
		assertEquals(m1.hashCode(), m2.hashCode());
	}

	@Test
	public void testToString() {
		String testUser = department_Model.toString();
		assertEquals(testUser, department_Model.toString());
	}

	@Test
	public void getAllTest() {
		List<Department_Model> test = department_Service.getAll();
		List<Department_Model> temp = department_Controller.getAll().getBody();
		assertEquals(test, temp);
	}

	@Test
	public void getAllEmptyTest() {
		List<Department_Model> test = department_Service.getAll();
		List<Department_Model> temp = department_Controller.getAll().getBody();
		assertEquals(test, temp);
	}

	@Test
	public void getBynameTest() throws DepartmentNameNotFoundException, DepartmentAlreadyExistException {
		Department_Model department_Model = new Department_Model(1, "AA", "Has to cook food", 10);
		department_Controller.addDepartment(department_Model);
		Department_Model test = department_Service.getByDepartmentName("AA");
		Department_Model temp = department_Controller.getByDepartmentName("AA").getBody();
		assertEquals(test, temp);
		department_Controller.deleteDepartment("AA");
	}

	@Test
	public void getnamenullTest() {
			Exception ex = assertThrows(DepartmentNameNotFoundException.class, () -> {
				department_Service.getByDepartmentName("as");
		    });
			
			String actual = "No such department found for the given departmentName. Please provide a correct departmentName";
			String expected = ex.getMessage();
			assertEquals(actual, expected);
	}
	
	@Test
	public void deletenullTest() {
			Exception ex = assertThrows(DepartmentNameNotFoundException.class, () -> {
				department_Service.deleteDepartment("as");
		    });
			
			String actual = "No such department found for the given departmentName. Please provide a correct departmentName";
			String expected = ex.getMessage();
			assertEquals(actual, expected);
	}
	
	

	@Test
	public void testGlobalExceptionHandler() throws DepartmentNameNotFoundException {
		DepartmentNameNotFoundException ex = assertThrows(DepartmentNameNotFoundException.class, () -> {
			throw new DepartmentNameNotFoundException("Global Exception Test");
		});
		ResponseEntity<String> response = globalDepartmentExceptionHandler.handleGuestNotFoundException(ex);

		String actual = "Global Exception Test";
		String expected = response.getBody();
		assertEquals(actual, expected);
	}

	@Test
	public void testGlobalExceptionHandler2() throws DepartmentAlreadyExistException {
		DepartmentAlreadyExistException ex = assertThrows(DepartmentAlreadyExistException.class, () -> {
			throw new DepartmentAlreadyExistException("Global Exception Test");
		});
		ResponseEntity<String> response = globalDepartmentExceptionHandler.handleDepartmentAlreadyExistException(ex);

		String actual = "Global Exception Test";
		String expected = response.getBody();
		assertEquals(actual, expected);
	}

	@Test
	public void testGlobalExceptionHandler3() throws Exception {
		Exception ex = assertThrows(Exception.class, () -> {
			throw new Exception("Global Exception Test");
		});
		ResponseEntity<String> response = globalDepartmentExceptionHandler.handleAllException(ex);

		String actual = "Global Exception Test";
		String expected = response.getBody();
		assertEquals(actual, expected);
	}

	@Test
	public void deleteDepartmentTest() throws DepartmentNameNotFoundException, DepartmentAlreadyExistException {
		Department_Model department_Model1 = new Department_Model(1, "Maali", "Has to serve food", 11);
		department_Controller.addDepartment(department_Model1);
		String test = department_Service.deleteDepartment("Maali");
		Department_Model department_Model2 = new Department_Model(1, "Maali", "Has to serve food", 11);
		department_Controller.addDepartment(department_Model2);
		String temp = department_Controller.deleteDepartment("Maali").getBody();
		assertEquals(test, temp);
	}

	@Test
	public void updateNoOfEmployeesTest() throws DepartmentNameNotFoundException, DepartmentAlreadyExistException {
		Department_Model department_Model2 = new Department_Model(1, "Q", "Has to cook food", 10);
		department_Controller.addDepartment(department_Model2);
		Department_Model test = department_Service.updateNoOfEmployees("Q", 7);
		Department_Model temp = department_Controller.updateNoOfEmployees("Q", 7).getBody();
		assertEquals(test, temp);
		department_Controller.deleteDepartment("Q");
	}

	@Test
	public void updateDeptDescTest() throws DepartmentAlreadyExistException, DepartmentNameNotFoundException {
		Department_Model department_Model3 = new Department_Model(1, "W", "Has to cook food", 10);
		department_Controller.addDepartment(department_Model3);
		Department_Model test = department_Service.updateDescription("W", "They have to maintain the garden.");
		Department_Model temp = department_Controller.updateDescription("W", "They have to maintain the garden.")
				.getBody();
		assertEquals(test, temp);
		department_Controller.deleteDepartment("W");
	}

}
