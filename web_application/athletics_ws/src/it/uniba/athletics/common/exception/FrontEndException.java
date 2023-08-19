package it.uniba.athletics.common.exception;

public class FrontEndException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FrontEndException(String message, Throwable cause) {
		super(message, cause);
	}

	public FrontEndException(String message) {
		super(message);
	}

	public FrontEndException(Throwable cause) {
		super(cause);
	}
	
}