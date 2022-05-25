package com.example.hr.controller.handler;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.hr.dto.error.ErrorResponse;

@RestControllerAdvice
public class RestErrorHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleAllOtherExceptions(Exception e) {
		return new ErrorResponse("FAILED", e.getMessage());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
		var violations = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
				.collect(Collectors.joining("|"));
		return new ErrorResponse("validation violation", violations);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		var violations = e.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining("|"));
		return new ErrorResponse("validation violation", violations);
	}
}

