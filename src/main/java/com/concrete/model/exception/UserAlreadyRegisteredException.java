package com.concrete.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.concrete.model.to.MessageError;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyRegisteredException extends BusinessException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 * @param cause
	 */
	public UserAlreadyRegisteredException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public UserAlreadyRegisteredException(final String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public UserAlreadyRegisteredException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param messageError
	 */
	public UserAlreadyRegisteredException(final MessageError messageError) {
		super(messageError);
	}
}
