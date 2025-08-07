package com.staff.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.staff.exception.InvalidStaffException;
import com.staff.exception.StaffAlreadyExistException;
import com.staff.model.Staff_Model;


@Service
public interface Staff_Service {
	
	public List<Staff_Model> getAll();
	public Staff_Model getByEmail(String email) throws InvalidStaffException;
	public Staff_Model addStaff(Staff_Model user) throws StaffAlreadyExistException;
	public String deleteStaff(String email) throws InvalidStaffException;
	public Staff_Model updateSalary(String email,int value) throws InvalidStaffException;
	public Staff_Model updateAddress(String email,String value);
	public Staff_Model updateName(String email,String value);

}
