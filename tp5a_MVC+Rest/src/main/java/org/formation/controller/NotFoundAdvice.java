package org.formation.controller;

import java.util.Date;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class NotFoundAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {EntityNotFoundException.class})
	ResponseEntity<Object> handleNotFoundException(HttpServletRequest request, 	Throwable ex) {
		ErrorDto dto = new ErrorDto();
		dto.setDate(new Date());
		dto.setMsg(ex.getMessage());
		dto.setSeverity("Pas grave");
		return new ResponseEntity<>(dto, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
}
