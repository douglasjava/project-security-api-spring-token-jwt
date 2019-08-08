package com.montreal.mock.montrealauth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class HttpUnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public HttpUnauthorizedException(String message) {
		super(message);
	}

}
