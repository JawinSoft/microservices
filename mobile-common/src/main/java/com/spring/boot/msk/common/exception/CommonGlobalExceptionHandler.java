package com.spring.boot.msk.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring.boot.msk.common.dto.Error;
import com.spring.boot.msk.common.dto.Errors;
import com.spring.boot.msk.common.dto.Response;

@ControllerAdvice
public class CommonGlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response<Errors>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Response<Errors> response = buildErrorRespone(ex.getBindingResult());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<Response<Errors>> handleThrowable(Throwable ex) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);

		String stackTrace = sw.toString();
		Error error = Error.builder().code(10009).message(ex.getMessage()).build();
		Errors erros = Errors.builder().errors(Arrays.asList(error)).build();

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(Response.<Errors>builder().response(erros).build());
	}

	private Response<Errors> buildErrorRespone(BindingResult bindingResult) {
		List<Error> errorDetails = bindingResult.getAllErrors().stream()
				.map(error -> Error.builder().code(1001).message(error.getDefaultMessage()).build())
				.collect(Collectors.toList());

		Errors errors = Errors.builder().errors(errorDetails).build();

		return Response.<Errors>builder().response(errors).build();

	}

}
