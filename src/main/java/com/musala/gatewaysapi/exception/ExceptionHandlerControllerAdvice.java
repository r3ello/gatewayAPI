/**
 * 
 */
package com.musala.gatewaysapi.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Rafael Bello Lara
 *
 */
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {


	@ResponseBody
	@ExceptionHandler(GatewayNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String gatewayNotFoundHandler(GatewayNotFoundException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(PeripheralNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String peripheralNotFoundHandler(PeripheralNotFoundException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(LimitOfPeripheralsExcededException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String limitOfPeripheralsExcededHandler(LimitOfPeripheralsExcededException ex) {
		return ex.getMessage();
	}
}
