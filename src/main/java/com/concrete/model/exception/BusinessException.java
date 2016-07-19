/**
 * 
 */
package com.concrete.model.exception;

import com.concrete.model.to.MessageError;

/**
 * @author Arnaud Santana Alves
 * @since 18/07/2016
 *
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	private MessageError messageError;

	/**
	 * @param message
	 *            message
	 * 
	 * @param cause
	 *            cause
	 */
	public BusinessException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 *            message
	 */
	public BusinessException(final String message) {
		super(message);
	}

	/**
	 * @param cause
	 *            cause
	 */
	public BusinessException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param cause
	 *            cause
	 */
	public BusinessException(final MessageError messageError) {
		this.messageError = messageError;
	}

	public MessageError getMessageError() {
		return messageError;
	}

}
