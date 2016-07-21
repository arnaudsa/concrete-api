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
public class LoginAuthenticationException extends BusinessException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 * @param cause
	 */
	public LoginAuthenticationException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public LoginAuthenticationException(final String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public LoginAuthenticationException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param messageError
	 */
	public LoginAuthenticationException(final MessageError messageError) {
		super(messageError);
	}
}
