package edu.asu.securebanking.exceptions;

/**
 * @author
 *
 * 		A business exception thrown by Service
 */
public class AppBusinessException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public AppBusinessException() {
		super();
	}

	/**
	 * @param message
	 */
	public AppBusinessException(final String message) {
		super(message);
	}

	/**
	 * @param t
	 */
	public AppBusinessException(final Throwable t) {
		super(t);
	}

	/**
	 * @param e
	 */
	public AppBusinessException(final Exception e) {
		super(e);
	}
}
