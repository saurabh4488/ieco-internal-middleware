package ieco.internal.middleware.exception;

/**
 * The type Internal technical exception.
 */
public class InternalTechnicalException extends CustomerException {

	/**
	 * Instantiates a new Internal technical exception.
	 *
	 * @param syncErrorCode the sync error code
	 * @param message       the message
	 */
	public InternalTechnicalException(CustomerErrorCode syncErrorCode, String message) {
		super(syncErrorCode, message);
	}

	/**
	 * Instantiates a new Internal technical exception.
	 *
	 * @param syncErrorCode the sync error code
	 * @param message       the message
	 * @param cause         the cause
	 */
	public InternalTechnicalException(CustomerErrorCode syncErrorCode, String message, Throwable cause) {
		super(syncErrorCode, message, cause);
	}
}
