package com.room.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRoomExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidation(MethodArgumentNotValidException ex){
		BindingResult result = ex.getBindingResult();
		List<String> errors = result.getFieldErrors().stream().map(er -> er.getDefaultMessage()).collect(Collectors.toList());
		return new ResponseEntity<String>(errors.toString(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidRoomException.class)
	public ResponseEntity<String> handleInvalidRoomIdException(InvalidRoomException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RoomAlreadyExistException.class)
	public ResponseEntity<String> handleRoomAlreadyExistException(RoomAlreadyExistException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidRoomTypeException.class)
	public ResponseEntity<String> handleInvalidRoomTypeException(InvalidRoomTypeException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
