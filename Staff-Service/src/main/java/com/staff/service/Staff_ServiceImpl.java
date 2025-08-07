package com.staff.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staff.exception.InvalidStaffException;
import com.staff.exception.StaffAlreadyExistException;
import com.staff.model.Staff_Model;
import com.staff.repository.Staff_Repo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Staff_ServiceImpl implements Staff_Service {

	@Autowired
	private Staff_Repo staff_Repo;

	/**
	 * This method is having a return type of List<Staff_Model>
	 * which stores the list of all staff details after fetching the data from
	 * the database using Repository.
	 */
	
	@Override
	public List<Staff_Model> getAll() {
		List<Staff_Model> list = staff_Repo.findAll();
		if (list.isEmpty()) {
			log.error("List is Empty!! Please add some data");
		} else {
			log.info("List of staff is fetched!!");
		}
		return list;
	}
	
	/**
	 * This method is having a return type of Staff_Model and
	 * it takes a String email as a parameter which stores Staff Model object
	 * after fetching the data according to the given parameter from the database
	 * using Repository.
	 */

	@Override
	public Staff_Model getByEmail(String email) throws InvalidStaffException {
		Staff_Model temp = staff_Repo.findByEmail(email);
		if (temp == null) {
			log.error("No data is present for the email " + email);
			throw new InvalidStaffException("No data is present for the email " + email);
		} else {
			log.info("Data is fetched for the email " + email);
		}
		return temp;
	}
	
	/**
	 * This method is having a return type of Staff_Model and
	 * it takes a Staff Model as a parameter which adds Staff Model object
	 * into the database using Repository.
	 */

	@Override
	public Staff_Model addStaff(Staff_Model user) throws StaffAlreadyExistException {
		Staff_Model staff = staff_Repo.findByEmail(user.getEmail());
		if(staff!=null) {
			log.error("Staff exists for the given email");
			throw new StaffAlreadyExistException("Staff exists for the given email");
		}
		log.info("Data added successfully");
		return staff_Repo.save(user);
	}
	
	/**
	 * This method is having a return type of String and it takes a
	 * String email as a parameter and it stores the message after deleting the data
	 * according to the given parameter from the database using Repository.
	 */

	@Override
	public String deleteStaff(String email) throws InvalidStaffException {
		Staff_Model temp = staff_Repo.findByEmail(email);
		if(temp==null) {
			log.error("Staff does not exist for the given email. Please enter a valid staff email");
			throw new InvalidStaffException("Staff does not exist for the given email. Please enter a valid staff email");
		}
		staff_Repo.deleteByEmail(email);
		log.info("Staff with email " + email + " deleted successfully!");
		return "Staff with email " + email + " deleted successfully";
	}
	
	/**
	 * This method is having a return type of Staff_Model and
	 * it takes a String email and integer value as a parameter which stores Staff
	 * Model object after updating the salary of the staff according to the given
	 * parameter in the database using Repository.
	 */

	@Override
	public Staff_Model updateSalary(String email, int value) throws InvalidStaffException {
		Staff_Model temp = staff_Repo.findByEmail(email);
		if (temp == null) {
			log.error("Can't update salary of an unknown email. Please provide a correct email");
			throw new InvalidStaffException("Can't update salary of an unknown email. Please provide a correct email");
		}
		temp.setSalary(value);
		log.info("Staff salary updated successfully!!");
		return staff_Repo.save(temp);
	}
	
	/**
	 * This method is having a return type of Staff_Model and
	 * it takes a String email and String value as a parameter which stores Staff
	 * Model object after updating the address of the staff according to the given
	 * parameter in the database using Repository.
	 */

	@Override
	public Staff_Model updateAddress(String email, String value) {
		Staff_Model temp = staff_Repo.findByEmail(email);
		temp.setAddress(value);
		return staff_Repo.save(temp);
	}
	
	/**
	 * This method is having a return type of Staff_Model and
	 * it takes a String email and String value as a parameter which stores Staff
	 * Model object after updating the name of the staff according to the given
	 * parameter in the database using Repository.
	 */

	@Override
	public Staff_Model updateName(String email, String value) {
		Staff_Model temp = staff_Repo.findByEmail(email);
		temp.setName(value);
		return staff_Repo.save(temp);
	}

	

}
