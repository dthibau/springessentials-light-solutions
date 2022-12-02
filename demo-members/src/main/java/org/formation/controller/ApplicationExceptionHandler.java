package org.formation.controller;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	

	@ExceptionHandler(value = {EntityNotFoundException.class})
	ResponseEntity<ErrorDto> handleEntityNotFoundException(HttpServletRequest request, Throwable ex) {
		
		return new ResponseEntity<ErrorDto>(
				ErrorDto.builder()
				        .apiVersion("1.0")
				        .level("WARN")
				        .message(ex.getMessage()).build()
				        ,HttpStatus.NOT_FOUND);
	}
}
