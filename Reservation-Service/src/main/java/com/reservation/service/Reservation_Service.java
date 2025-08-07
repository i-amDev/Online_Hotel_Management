package com.reservation.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.reservation.exception.ReservationAlreadyExistException;
import com.reservation.exception.ReservationNotFoundException;
import com.reservation.model.Reservation_Model;
import com.reservation.model.Room_Model;

@Service
public interface Reservation_Service{
	
	public List<Reservation_Model> getAll();
//	public List<Room_Model> getAllRoom();
	public Reservation_Model getByPhoneNumber(long phoneNumber) throws ReservationNotFoundException;
	public Reservation_Model addReservation(Reservation_Model reservation) throws ReservationAlreadyExistException;
	public String deleteReservation(long phoneNumber);
//	public Reservation_Model update(long phoneNumber,String parameter,String value);
//	public double calculateBill(long phoneNumber,String roomType,int noOfRooms) throws ReservationIdNotFoundException;
	public Reservation_Model updateName(long phoneNumber,String value);
	public Reservation_Model updateAddress(long phoneNumber,String value);

}
