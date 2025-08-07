package com.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room_Model {
	
	private String roomId;
	private int roomCharge;
	private String roomType;
	private int noOfRooms;
	private boolean roomAvailability;

}
