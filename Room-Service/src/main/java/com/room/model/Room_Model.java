package com.room.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "room_collection")
@AllArgsConstructor
@NoArgsConstructor
public class Room_Model {
	
	@Id
	private String roomId;
	@Min(value = 1,message = "Room charge should be greater than or equal to 1")
	private int roomCharge;
	@NotBlank(message = "Room type should not be empty")
	private String roomType;
	@Min(value = 1,message = "No of rooms should be greater than or equal to 1")
	private int noOfRooms;

}
