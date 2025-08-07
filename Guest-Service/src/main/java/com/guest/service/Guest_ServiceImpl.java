package com.guest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guest.exception.GuestAlreadyExistException;
import com.guest.exception.GuestNotFoundException;
import com.guest.model.Guest_Model;
import com.guest.repository.Guest_Repo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Guest_ServiceImpl implements Guest_Service {

	@Autowired
	private Guest_Repo guest_Repo;

	/**
	 * This method is having a return type of List<Guest_Model>
	 * which stores the list of all guest details after fetching the data from
	 * the database using Repository.
	 */
	
	@Override
	public List<Guest_Model> getAll() {
		List<Guest_Model> list = guest_Repo.findAll();
		if (list.isEmpty()) {
			log.error("List is Empty!! Please add some data");
		} else {
			log.info("List of guest is fetched!!");
		}
		return list;
	}
	
	/**
	 * This method is having a return type of Guest_Model and
	 * it takes a long phoneNumber as a parameter which stores Guest Model object
	 * after fetching the data according to the given parameter from the database
	 * using Repository.
	 */

	@Override
	public Guest_Model getByPhoneNumber(long phoneNumber) throws GuestNotFoundException {
		Guest_Model temp = guest_Repo.findByPhoneNumber(phoneNumber);
		if (temp == null) {
			log.error("No data is present for the phoneNumber " + phoneNumber);
			throw new GuestNotFoundException("No guest found for an unknown phoneNumber. Please provide a correct phoneNumber");
		} else {
			log.info("Data is fetched for the phoneNumber " + phoneNumber);
		}
		return temp;
	}
	
	/**
	 * This method is having a return type of Guest_Model and
	 * it takes a Guest Model as a parameter which adds Guest Model object
	 * into the database using Repository.
	 */

	@Override
	public Guest_Model addGuest(Guest_Model user) throws GuestAlreadyExistException {
		Guest_Model temp = guest_Repo.findByEmail(user.getEmail());
		Guest_Model temp2 = guest_Repo.findByPhoneNumber(user.getPhoneNumber());
		if(temp!=null || temp2!=null) {
			log.error("Guest already exists with the given email or phone number");
			throw new GuestAlreadyExistException("Guest already exists with the given email or phone number");
		}		
		log.info("Guest added successfully");
		return guest_Repo.save(user);
	}
	
	/**
	 * This method is having a return type of String and it takes a
	 * long phoneNumber as a parameter and it stores the message after deleting the data
	 * according to the given parameter from the database using Repository.
	 */

	@Override
	public String deleteGuest(long phoneNumber) {
		guest_Repo.deleteByPhoneNumber(phoneNumber);
		log.info("Guest with phoneNumber " + phoneNumber + " deleted successfully!");
		return "Guest with phoneNumber " + phoneNumber + " deleted successfully";
	}
	
	/**
	 * This method is having a return type of Guest_Model and
	 * it takes a long phoneNumber and String value as a parameter which stores Guest
	 * Model object after updating the name of the guest according to the given
	 * parameter in the database using Repository.
	 */

	@Override
	public Guest_Model updateName(long phoneNumber, String value) throws GuestNotFoundException {
		Guest_Model temp = guest_Repo.findByPhoneNumber(phoneNumber);
		if (temp == null) {
			throw new GuestNotFoundException(
					"Can't update the name of an unknown phoneNumber. Please provide a correct phoneNumber");
		}
		temp.setName(value);
		log.info("Name updated successfully!!");
		return guest_Repo.save(temp);
	}
	
	/**
	 * This method is having a return type of Guest_Model and
	 * it takes a long phoneNumber and String value as a parameter which
	 * stores Department Model object after updating the email of the guest according
	 * to the given value in the database using Repository.
	 */
	
	@Override
	public Guest_Model updateEmail(long phoneNumber, String value) throws GuestNotFoundException {
		Guest_Model temp = guest_Repo.findByPhoneNumber(phoneNumber);
		if (temp == null) {
			throw new GuestNotFoundException(
					"Can't update the email of an unknown phoneNumber. Please provide a correct phoneNumber");
		}
		temp.setEmail(value);
		log.info("Email updated successfully!!");
		return guest_Repo.save(temp);
	}

}
