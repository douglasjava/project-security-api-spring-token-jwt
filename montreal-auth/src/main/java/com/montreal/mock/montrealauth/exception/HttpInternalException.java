package com.montreal.mock.montrealauth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class HttpInternalException extends RuntimeException {

	private static final long serialVersionUID = 6830956287428659979L;

	public HttpInternalException(String message) {
		super(message);
	}

}
