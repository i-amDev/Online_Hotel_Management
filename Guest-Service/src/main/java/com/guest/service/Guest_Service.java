package com.guest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.guest.exception.GuestAlreadyExistException;
import com.guest.exception.GuestNotFoundException;
import com.guest.model.Guest_Model;

@Service
public interface Guest_Service {
	
	public List<Guest_Model> getAll();
	public Guest_Model getByPhoneNumber(long phoneNumber) throws GuestNotFoundException;
	public Guest_Model addGuest(Guest_Model user) throws GuestAlreadyExistException;
	public String deleteGuest(long phoneNumber);
	public Guest_Model updateName(long phoneNumber,String value) throws GuestNotFoundException;
	public Guest_Model updateEmail(long phoneNumber,String value) throws GuestNotFoundException;

}
