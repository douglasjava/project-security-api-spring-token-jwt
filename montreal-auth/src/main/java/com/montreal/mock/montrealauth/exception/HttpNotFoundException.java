package com.montreal.mock.montrealauth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public final class HttpNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2155803295707295989L;

	public HttpNotFoundException(String message) {
		super(message);
	}

}
