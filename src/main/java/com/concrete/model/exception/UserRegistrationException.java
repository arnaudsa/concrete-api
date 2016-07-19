/**
 * 
 */
package com.concrete.model.exception;

import org.apache.log4j.Logger;

import com.concrete.model.to.MessageError;

/**
 * @author Arnaud Santana Alves
 * @since 17/07/2016
 *
 */
public class UserRegistrationException extends BusinessException {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(UserRegistrationException.class);

	/**
	 * @param message
	 *            message
	 * 
	 * @param cause
	 *            cause
	 */
	public UserRegistrationException(final String message, final Throwable cause) {
		super(message, cause);
		LOGGER.error(message, cause);
	}

	/**
	 * @param message
	 *            message
	 */
	public UserRegistrationException(final String message) {
		super(message);
		LOGGER.error(message);
	}

	/**
	 * @param cause
	 *            cause
	 */
	public UserRegistrationException(final Throwable cause) {
		super(cause);
		LOGGER.error(cause);
	}

	public UserRegistrationException(final MessageError messageError) {
		super(messageError);
		LOGGER.error(messageError.getMensagens());
	}
}
