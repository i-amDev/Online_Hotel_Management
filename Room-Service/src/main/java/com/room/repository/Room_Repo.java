package com.room.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.room.model.Room_Model;

@Repository
public interface Room_Repo extends MongoRepository<Room_Model, String>{
	
	public Room_Model findByRoomType(String roomType);
	
	public String deleteByRoomType(String roomType);

}
