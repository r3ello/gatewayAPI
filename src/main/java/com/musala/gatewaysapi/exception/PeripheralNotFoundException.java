package com.musala.gatewaysapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Peripheral not found")
public class PeripheralNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PeripheralNotFoundException(String message) {
		super(message);
	}
	public PeripheralNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
