package com.qualiti.odontoSystem.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseBody
public class CustomValidationException extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException exception,
			WebRequest request) {
		
//		this takes all the violations to model constraints and puts their messages into a list
		List<String> details = exception.getConstraintViolations().parallelStream().map(e -> e.getMessage())
				.collect(Collectors.toList());

		ErrorResponse error = new ErrorResponse(details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
