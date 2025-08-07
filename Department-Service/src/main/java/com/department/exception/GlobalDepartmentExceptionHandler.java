package com.department.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalDepartmentExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllException(Exception ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(DepartmentNameNotFoundException.class)
	public ResponseEntity<String> handleGuestNotFoundException(DepartmentNameNotFoundException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DepartmentAlreadyExistException.class)
	public ResponseEntity<String> handleDepartmentAlreadyExistException(DepartmentAlreadyExistException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidation(MethodArgumentNotValidException ex){
		BindingResult result = ex.getBindingResult();
		List<String> errors = result.getFieldErrors().stream().map(er -> er.getDefaultMessage()).collect(Collectors.toList());
		return new ResponseEntity<String>(errors.toString(), HttpStatus.BAD_REQUEST);
	}
	
	

}
