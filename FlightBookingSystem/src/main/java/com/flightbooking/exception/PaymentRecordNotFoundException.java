package com.flightbooking.exception;

public class PaymentRecordNotFoundException extends RuntimeException{
	public PaymentRecordNotFoundException(String message) {
		super(message);
	}

}
