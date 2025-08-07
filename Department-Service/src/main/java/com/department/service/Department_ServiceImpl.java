package com.department.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.department.exception.DepartmentAlreadyExistException;
import com.department.exception.DepartmentNameNotFoundException;
import com.department.model.Department_Model;
import com.department.repository.Department_Repo;
import com.department.sequence.SequenceService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Department_ServiceImpl implements Department_Service {

	@Autowired
	private Department_Repo department_Repo;
	
	@Autowired
	private SequenceService sequenceService;
	

	
	/**
	 * This method is having a return type of List<Department_Model>
	 * which stores the list of all department details after fetching the data from
	 * the database using Repository.
	 */
	@Override
	public List<Department_Model> getAll() {
		List<Department_Model> list = department_Repo.findAll();
		if (list.isEmpty()) {
			log.error("List is Empty!! Please add some data");
		} 
		else {
			log.info("List of department is fetched!!");
		}
		return list;
	}
	
	/**
	 * This method is having a return type of Department_Model and
	 * it takes a String departmentName as a parameter which stores Department Model object
	 * after fetching the data according to the given parameter from the database
	 * using Repository.
	 */

	@Override
	public Department_Model getByDepartmentName(String departmentName) throws DepartmentNameNotFoundException {
		Department_Model temp = department_Repo.findByDepartmentName(departmentName);
		if (temp == null) {
			log.error("No data is present for the departmentName " + departmentName);
			throw new DepartmentNameNotFoundException(
					"No such department found for the given departmentName. Please provide a correct departmentName");
		} 
		else {
			log.info("Data is fetched for the departmentName " + departmentName);
		}
		return temp;
	}
	
	/**
	 * This method is having a return type of Department_Model and
	 * it takes a Department Model as a parameter which adds Department Model object
	 * into the database using Repository.
	 */

	@Override
	public Department_Model addDepartment(Department_Model department) throws DepartmentAlreadyExistException {
		Department_Model temp = department_Repo.findByDepartmentName(department.getDepartmentName());
		System.out.println(temp);
		if(temp!=null) {
			throw new DepartmentAlreadyExistException("Department already exists for the given department name");
		}
		log.info("Department added successfully");
		department.setDepartmentId(sequenceService.getNextSequence("deptId"));
		return department_Repo.save(department);
	}
	
	/**
	 * This method is having a return type of String and it takes a
	 * String departmentName as a parameter and it stores the message after deleting the data
	 * according to the given parameter from the database using Repository.
	 */

	@Override
	public String deleteDepartment(String departmentName) throws DepartmentNameNotFoundException{
		Department_Model temp = department_Repo.findByDepartmentName(departmentName);
		if (temp == null) {
			log.error("No such department found for the given departmentName. Please provide a correct departmentName");
			throw new DepartmentNameNotFoundException(
					"No such department found for the given departmentName. Please provide a correct departmentName");
		}
		department_Repo.deleteByDepartmentName(departmentName);
		log.info("Department with departmentName " + departmentName + " deleted successfully!");
		return "Department with departmentName " + departmentName + " deleted successfully!";
	}
	
	/**
	 * This method is having a return type of Department_Model and
	 * it takes a String departmentName and Integer value as a parameter which stores Department
	 * Model object after updating the number of employees according to the given
	 * parameter in the database using Repository.
	 */

	@Override
	public Department_Model updateDescription(String departmentName,String value) {
		Department_Model temp = department_Repo.findByDepartmentName(departmentName);
		temp.setDepartmentDescription(value);
		log.info("Department description updated successfully");
		return department_Repo.save(temp);
	}
	
	/**
	 * This method is having a return type of Department_Model and
	 * it takes a String departmentName and String value as a parameter which
	 * stores Department Model object after updating the department description according
	 * to the given value in the database using Repository.
	 */

	@Override
	public Department_Model updateNoOfEmployees(String departmentName, int value) throws DepartmentNameNotFoundException {
		Department_Model temp = department_Repo.findByDepartmentName(departmentName);
		if (temp == null) {
			throw new DepartmentNameNotFoundException(
					"Can't update the no of employees for the given departmentName. Please provide a correct departmentName");
		}
		temp.setNoOfEmployees(value);
		log.info("No of employees updated successfully!!");
		return department_Repo.save(temp);
	}

}
