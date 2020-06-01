package com.spring.boot.msk.country.exception;

import java.util.Arrays;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring.boot.msk.common.dto.Error;
import com.spring.boot.msk.common.dto.Errors;
import com.spring.boot.msk.common.dto.Response;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Response<Errors>> hadnleEmptyResultDataAccessException(
			EmptyResultDataAccessException exception) {
		return prepareErrorResponse(10020, "No Country Found with Given CountryCode in DataBase ",
				exception.getMessage());
	}

	@ExceptionHandler(IncorrectResultSizeDataAccessException.class)
	public ResponseEntity<Response<Errors>> hadnleIncorrectResultSizeDataAccessException(
			IncorrectResultSizeDataAccessException exception) {
		return prepareErrorResponse(10020, "No Unique with Given CountryCode in DataBase ", exception.getMessage());
	}

	private ResponseEntity<Response<Errors>> prepareErrorResponse(int errorCode, String message, String errorMessage) {
		Error error = Error.builder().code(errorCode).message(message + errorMessage).build();

		Errors errors = Errors.builder().errors(Arrays.asList(error)).build();

		return ResponseEntity.badRequest().body(Response.<Errors>builder().response(errors).build());
	}

}
