package com.concrete.mock;

/**
 * Exception lançada ao ocorrer problemas na geração dos Mocks.
 * 
 * @author Arnaud Santana Alves
 * @since 17/07/2016
 * @see Exception
 */
public class MockGeneratorException extends RuntimeException {

	private static final long serialVersionUID = 154920967870312594L;

	/**
	 * Constructor
	 */
	public MockGeneratorException() {
	}

	/**
	 * Contructor
	 * 
	 * @param message
	 *            Mensagem da exception.
	 */
	public MockGeneratorException(final String message) {
		super(message);
	}

	/**
	 * Contructor
	 * 
	 * @param throwable
	 *            PrintStrackTrace da exce��o.
	 */
	public MockGeneratorException(final Throwable throwable) {
		super(throwable);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 *            Mensagem da exce��o.
	 * @param throwable
	 *            PrintStrackTrace da exce��o.
	 */
	public MockGeneratorException(final String message, final Throwable throwable) {
		super(message, throwable);
	}

}
