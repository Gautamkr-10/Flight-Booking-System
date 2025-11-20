package com.flightbooking.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.flightbooking.dto.ResponseStructure;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(NoRecordFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleNRAE(NoRecordFoundException exception){
		ResponseStructure<String> response=new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("failure");
		response.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PassengerNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlePNFE(PassengerNotFoundException e) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setData(e.getMessage());
		response.setMessage("Failed");

		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(PaymentRecordNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlePRNFE(PaymentRecordNotFoundException e) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setData(e.getMessage());
		response.setMessage("No Payments");

		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.NOT_FOUND);

	}

}
