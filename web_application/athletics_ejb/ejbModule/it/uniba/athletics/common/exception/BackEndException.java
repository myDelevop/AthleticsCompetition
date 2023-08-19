package it.uniba.athletics.common.exception;

public class BackEndException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BackEndException(String message, Throwable cause) {
		super(message, cause);
	}

	public BackEndException(String message) {
		super(message);
	}

	public BackEndException(Throwable cause) {
		super(cause);
	}
	
}