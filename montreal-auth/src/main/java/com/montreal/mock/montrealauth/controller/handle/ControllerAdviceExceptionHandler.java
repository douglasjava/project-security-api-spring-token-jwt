package com.montreal.mock.montrealauth.controller.handle;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.montreal.mock.montrealauth.exception.HttpBadRequestException;
import com.montreal.mock.montrealauth.exception.HttpUnauthorizedException;
import com.montreal.mock.montrealauth.response.ErrorResponse;

@ControllerAdvice
public class ControllerAdviceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(HttpBadRequestException.class)
	public ResponseEntity<ErrorResponse> badRequestException(HttpBadRequestException e) {
		ErrorResponse errorResponse = getErrorResponse(e, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpUnauthorizedException.class)
	public ResponseEntity<ErrorResponse> unauthorizedException(HttpBadRequestException e) {
		ErrorResponse errorResponse = getErrorResponse(e, HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}

	private ErrorResponse getErrorResponse(RuntimeException e, HttpStatus httpStatus) {
		return new ErrorResponse(httpStatus, LocalDateTime.now(), e.getMessage(), e.getLocalizedMessage());
	}
}
