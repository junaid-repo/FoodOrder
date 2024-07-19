package com.food.customers.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	Map<String, String> mapErrors(MethodArgumentNotValidException ex) {

		Map<String, String> errMap = new HashMap<>();

		ex.getBindingResult().getAllErrors().stream().forEach(err -> {
			errMap.put(((FieldError) err).getField(), err.getDefaultMessage());
		});
		return errMap;
	}

}
