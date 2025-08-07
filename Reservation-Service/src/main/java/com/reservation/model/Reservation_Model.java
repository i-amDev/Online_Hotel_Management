package com.reservation.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "reservation_collection")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation_Model {
	
	@Id
	private String bookingId;
	@NotBlank(message = "Name should not be blank")
	private String name;
	@Min(value = 18,message = "Age should be greater than or equal to 18")
	private int age;
	@NotBlank(message = "Address should not be blank")
	private String address;
	@Min(value = 1000000000, message = "Phone number length should be equal to 10")
	@Max(value = 9999999999L, message = "Phone number length should be equal to 10")
	private long phoneNumber;
	@Min(value = 1, message = "No of guests should be greater than or equal to 1")
	private int noOfGuests;
	private int noOfRooms;
	@NotBlank(message = "Status should not be blank")
	private String status;
	private Date checkInDate;
	private Date checkOutDate;
	private int noOfNights;
	@NotBlank(message = "Room type should not be blank")
	private String roomType;
	private double billAmount;
	
}