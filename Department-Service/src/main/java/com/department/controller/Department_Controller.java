package com.department.controller;

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

import com.department.exception.DepartmentAlreadyExistException;
import com.department.exception.DepartmentNameNotFoundException;
import com.department.model.Department_Model;
import com.department.service.Department_Service;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/department")
public class Department_Controller {

	@Autowired
	private Department_Service department_Service;

	/**
	 * This method is having a return type of ResponseEntity<List<Department_Model>>
	 * which stores the list of all department details after fetching the data from
	 * the database using Get mapping.
	 */
	@GetMapping("/getall")
	public ResponseEntity<List<Department_Model>> getAll() {
		return new ResponseEntity<List<Department_Model>>(department_Service.getAll(), HttpStatus.OK);
	}

	/**
	 * This method is having a return type of ResponseEntity<Department_Model> and
	 * it takes a String departmentName as a parameter which stores Department Model object
	 * after fetching the data according to the given parameter from the database
	 * using Get mapping.
	 */
	@GetMapping("/{departmentName}")
	public ResponseEntity<Department_Model> getByDepartmentName(@PathVariable("departmentName") String departmentName)
			throws DepartmentNameNotFoundException {
		return new ResponseEntity<Department_Model>(department_Service.getByDepartmentName(departmentName),
				HttpStatus.OK);
	}

	/**
	 * This method is having a return type of ResponseEntity<Department_Model> and
	 * it takes a Department Model as a parameter which adds Department Model object
	 * into the database using Post mapping.
	 */
	@PostMapping("/adddepartment")
	public ResponseEntity<Department_Model> addDepartment(@Valid @RequestBody Department_Model department)
			throws DepartmentAlreadyExistException {
		return new ResponseEntity<Department_Model>(department_Service.addDepartment(department), HttpStatus.OK);
	}

	/**
	 * This method is having a return type of ResponseEntity<String> and it takes a
	 * String departmentName as a parameter and it stores the message after deleting the data
	 * according to the given parameter from the database using Delete mapping.
	 */
	@DeleteMapping("/delete/{departmentName}")
	public ResponseEntity<String> deleteDepartment(@PathVariable("departmentName") String departmentName)
			throws DepartmentNameNotFoundException {
		return new ResponseEntity<String>(department_Service.deleteDepartment(departmentName), HttpStatus.OK);
	}

	/**
	 * This method is having a return type of ResponseEntity<Department_Model> and
	 * it takes a String departmentName and Integer value as a parameter which stores Department
	 * Model object after updating the number of employees according to the given
	 * parameter in the database using Put mapping.
	 */
	@PutMapping("/updateNoOfEmployees/{departmentName}/{value}")
	public ResponseEntity<Department_Model> updateNoOfEmployees(@PathVariable("departmentName") String departmentName,
			@PathVariable("value") int value) throws DepartmentNameNotFoundException {
		return new ResponseEntity<Department_Model>(department_Service.updateNoOfEmployees(departmentName, value),
				HttpStatus.OK);
	}

	/**
	 * This method is having a return type of ResponseEntity<Department_Model> and
	 * it takes a String departmentName and String value as a parameter which
	 * stores Department Model object after updating the department description according
	 * to the given value in the database using Put mapping.
	 */
	@PutMapping("/updateDescription/{departmentName}/{value}")
	public ResponseEntity<Department_Model> updateDescription(@PathVariable("departmentName") String departmentName,
			@PathVariable("value") String value) {
		return new ResponseEntity<Department_Model>(department_Service.updateDescription(departmentName, value), HttpStatus.OK);
	}

}