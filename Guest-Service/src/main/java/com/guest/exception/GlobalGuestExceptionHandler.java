package com.guest.exception;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalGuestExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidation(MethodArgumentNotValidException ex){
		BindingResult result = ex.getBindingResult();
		List<String> errors = result.getFieldErrors().stream().map(er -> er.getDefaultMessage()).collect(Collectors.toList());
		return new ResponseEntity<String>(errors.toString(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllException(Exception ex, WebRequest request) {
		String response = new String(LocalDate.now() + " " + ex.getMessage() + " " +  request.getDescription(false) + " Internal Server Error");
		return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(GuestAlreadyExistException.class)
	public ResponseEntity<String> handleGuestAlreadyExistException(GuestAlreadyExistException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(GuestNotFoundException.class)
	public ResponseEntity<String> handleGuestIdNotFoundException(GuestNotFoundException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}

}
