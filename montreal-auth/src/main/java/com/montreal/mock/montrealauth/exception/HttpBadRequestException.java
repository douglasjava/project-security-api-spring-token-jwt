package com.montreal.mock.montrealauth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public final class HttpBadRequestException extends RuntimeException {

	private static final long serialVersionUID = 5680520528124260166L;

	public HttpBadRequestException(String message) {
		super(message);
	}

}
