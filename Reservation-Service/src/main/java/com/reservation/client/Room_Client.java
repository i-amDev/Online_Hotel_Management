package com.reservation.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.reservation.model.Room_Model;

@FeignClient(name="ROOM")
public interface Room_Client {
	
	@GetMapping("/room/getall")
	public List<Room_Model> getAll();
	
	@PutMapping("/room/updateNoOfRooms/{roomType}/{value}")
	public ResponseEntity<Room_Model> updateNoOfRooms(@PathVariable("roomType") String roomType,@PathVariable("value") int value);

}
