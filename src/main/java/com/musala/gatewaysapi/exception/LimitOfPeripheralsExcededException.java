package com.musala.gatewaysapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Limit of peripherals exceeded, only 10 peripheral by gateway are allowed")
public class LimitOfPeripheralsExcededException extends RuntimeException {

	private static final long serialVersionUID = 1L;


	public LimitOfPeripheralsExcededException(String message) {
		super(message);
	}
	public LimitOfPeripheralsExcededException(String message, Throwable cause) {
		super(message, cause);
	}
}
