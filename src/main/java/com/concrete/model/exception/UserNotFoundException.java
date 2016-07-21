/**
 * 
 */
package com.concrete.model.exception;

import com.concrete.model.to.MessageError;

/**
 * @author Arnaud Santana Alves
 * @since 20/07/2016
 *
 */
public class UserNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 * @param cause
	 */
	public UserNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public UserNotFoundException(final String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public UserNotFoundException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param messageError
	 */
	public UserNotFoundException(final MessageError messageError) {
		super(messageError);
	}
}
