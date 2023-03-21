package ieco.internal.middleware.exception;

/**
 * The type Customer exception.
 */
public class CustomerException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new Customer exception.
	 *
	 * @param syncErrorCode the sync error code
	 * @param message       the message
	 * @param cause         the cause
	 */
	public CustomerException(CustomerErrorCode syncErrorCode, String message, Throwable cause) {
		super(buildMessage(syncErrorCode,message, cause));
	}

	/**
	 * Instantiates a new Customer exception.
	 *
	 * @param syncErrorCode the sync error code
	 * @param message       the message
	 */
	public CustomerException(CustomerErrorCode syncErrorCode, String message) {
		super(buildMessage(syncErrorCode, message));
	}

	/**
	 * Builds the message.
	 *
	 * @param syncErrorCode the sync error code
	 * @param message the message
	 * @return the string
	 */
	private static String buildMessage(CustomerErrorCode syncErrorCode, String message) {
		return "[" + syncErrorCode.code() + "] " + message;
	}

	private static String buildMessage(CustomerErrorCode syncErrorCode, String message,Throwable cause) {
		return "[" + syncErrorCode.code() + "] " + message +" " + cause.getMessage();
	}

}
