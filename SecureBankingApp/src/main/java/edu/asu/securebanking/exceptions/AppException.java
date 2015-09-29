package edu.asu.securebanking.exceptions;

/**
 * @author
 * 
 * 		A general Exception class
 *
 */
public class AppException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public AppException() {
		super();
	}

	/**
	 * @param message
	 */
	public AppException(final String message) {
		super(message);
	}

	/**
	 * @param t
	 */
	public AppException(final Throwable t) {
		super(t);
	}

	/**
	 * @param e
	 */
	public AppException(final Exception e) {
		super(e);
	}
}
