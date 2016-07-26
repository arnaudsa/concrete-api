package com.concrete.model.exception;

import com.concrete.model.to.MessageError;

public class TokenNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param messageError
	 */
	public TokenNotFoundException(final MessageError messageError) {
		super(messageError);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public TokenNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public TokenNotFoundException(final String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public TokenNotFoundException(final Throwable cause) {
		super(cause);
	}

}
