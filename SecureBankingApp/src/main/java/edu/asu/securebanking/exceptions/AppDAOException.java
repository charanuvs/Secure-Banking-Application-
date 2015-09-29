package edu.asu.securebanking.exceptions;

/**
 * @author
 *
 * 		A DAO Exception thrown by all DAO classes
 */
public class AppDAOException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public AppDAOException() {
		super();
	}

	/**
	 * @param message
	 */
	public AppDAOException(final String message) {
		super(message);
	}

	/**
	 * @param t
	 */
	public AppDAOException(final Throwable t) {
		super(t);
	}

	/**
	 * @param e
	 */
	public AppDAOException(final Exception e) {
		super(e);
	}
}
