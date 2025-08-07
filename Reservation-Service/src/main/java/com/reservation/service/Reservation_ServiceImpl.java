package com.reservation.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservation.client.Room_Client;
import com.reservation.exception.ReservationAlreadyExistException;
import com.reservation.exception.ReservationNotFoundException;
import com.reservation.model.Reservation_Model;
import com.reservation.model.Room_Model;
import com.reservation.repository.Reservation_Repo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Reservation_ServiceImpl implements Reservation_Service {

	@Autowired
	private Reservation_Repo reservation_Repo;

	@Autowired
	private Room_Client room_Client;
	
//	public List<Room_Model> getAllRoom() {
//		return room_Client.getAll();
//	}
	
	/**
	 * This method is having a return type of List<Reservation_Model>
	 * which stores the list of all reservation details after fetching the data from
	 * the database using Repository.
	 */

	@Override
	public List<Reservation_Model> getAll() {
		List<Reservation_Model> list = reservation_Repo.findAll();
		if (list.isEmpty()) {
			log.error("List is Empty!! Please add some data");
		} else {
			log.info("List of reservation is fetched!!");
		}
		return list;
	}
	
	/**
	 * This method is having a return type of Reservation_Model and
	 * it takes a long phoneNumber as a parameter which stores Reservation Model object
	 * after fetching the data according to the given parameter from the database
	 * using Repository.
	 */

	@Override
	public Reservation_Model getByPhoneNumber(long phoneNumber) throws ReservationNotFoundException {
		Reservation_Model temp = reservation_Repo.findByPhoneNumber(phoneNumber);
		if (temp == null) {
			log.error("No data is present for the phoneNumber " + phoneNumber);
			throw new ReservationNotFoundException(
					"No such reservation found for the given phoneNumber. Please provide a correct phoneNumber");
		} else {
			log.info("Data is fetched for the phoneNumber " + phoneNumber);
		}
		return temp;
	}
	
	/**
	 * This method is having a return type of Reservation_Model and
	 * it takes a Reservation Model as a parameter which adds Reservation Model object
	 * into the database using Repository.
	 * 
	 * This add reservation method will only add the reservation in the database if there is no reservation 
	 * exists for the given phone number.
	 * 
	 * This method is also using Feign client to communicate to Room Service to fetch the room charges 
	 * so that the bill can be calculated.
	 */

	@Override
	public Reservation_Model addReservation(Reservation_Model reservation) throws ReservationAlreadyExistException {
		Reservation_Model temp = reservation_Repo.findByPhoneNumber(reservation.getPhoneNumber());
		if(temp!=null) {
			log.error("Reservation already exist for the given phone number");
			throw new ReservationAlreadyExistException("Reservation already exist for the given phone number");
		}
		Date checkin = reservation.getCheckInDate();
		Date checkout = reservation.getCheckOutDate();
		reservation.setNoOfNights((int)Math.abs(checkout.getTime()-checkin.getTime())/86400000);
		if(reservation.getNoOfGuests()%3==0) {
			reservation.setNoOfRooms(reservation.getNoOfGuests()/3);
		}
		else if(reservation.getNoOfGuests()%3!=0) {
			reservation.setNoOfRooms(reservation.getNoOfGuests()/3 + 1);
		}
		List<Room_Model> list = room_Client.getAll();
		double ans = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getRoomType().equals(reservation.getRoomType())) {
				if (reservation.getNoOfRooms() <= list.get(i).getNoOfRooms()) {
					ans = (reservation.getNoOfRooms() * list.get(i).getRoomCharge()) * reservation.getNoOfNights();
					room_Client.updateNoOfRooms(reservation.getRoomType(), list.get(i).getNoOfRooms()-reservation.getNoOfRooms());
				}
				else {
					throw new RuntimeException("These many rooms are not available for " + reservation.getRoomType() + " right now. Try choosing another room type.");
				}
			}
		}
		reservation.setBillAmount(ans);
		log.info("Reservation added successfully");
		return reservation_Repo.save(reservation);
	}
	
	/**
	 * This method is having a return type of String and it takes a
	 * long phoneNumber as a parameter and it stores the message after deleting the data
	 * according to the given parameter from the database using Repository.
	 */

	@Override
	public String deleteReservation(long phoneNumber) {
		Reservation_Model temp = reservation_Repo.findByPhoneNumber(phoneNumber);
		List<Room_Model> list = room_Client.getAll();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getRoomType().equals(temp.getRoomType())) {
				room_Client.updateNoOfRooms(temp.getRoomType(), list.get(i).getNoOfRooms()+temp.getNoOfRooms());
			}
		}
		reservation_Repo.deleteByPhoneNumber(phoneNumber);
		log.info("Reservation with phoneNumber " + phoneNumber + " deleted successfully!");
		return "Reservation with phoneNumber " + phoneNumber + " deleted successfully!";
	}
	
	/**
	 * This method is having a return type of Reservation_Model and
	 * it takes a long phoneNumber and String value as a parameter which stores Reservation
	 * Model object after updating the name of the guest according to the given
	 * parameter in the database using Repository.
	 */

	@Override
	public Reservation_Model updateName(long phoneNumber, String value) {
		Reservation_Model temp = reservation_Repo.findByPhoneNumber(phoneNumber);
		temp.setName(value);
		return reservation_Repo.save(temp);
	}
	
	/**
	 * This method is having a return type of Guest_Model and
	 * it takes a long phoneNumber and String value as a parameter which
	 * stores Reservation Model object after updating the address of the guest according
	 * to the given value in the database using Repository.
	 */

	@Override
	public Reservation_Model updateAddress(long phoneNumber, String value) {
		Reservation_Model temp = reservation_Repo.findByPhoneNumber(phoneNumber);
		temp.setAddress(value);
		return reservation_Repo.save(temp);
	}


}
