package ieco.internal.middleware.exception;

/**
 * The type Internal business exception.
 */
public class InternalBusinessException extends CustomerException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8862044456988595587L;

	/**
	 * Instantiates a new Internal business exception.
	 *
	 * @param syncErrorCode the sync error code
	 * @param message       the message
	 */
	public InternalBusinessException(CustomerErrorCode syncErrorCode, String message) {
		super(syncErrorCode, message);
	}

	/**
	 * Instantiates a new Internal business exception.
	 *
	 * @param syncErrorCode the sync error code
	 * @param message       the message
	 * @param cause         the cause
	 */
	public InternalBusinessException(CustomerErrorCode syncErrorCode, String message, Throwable cause) {
		super(syncErrorCode, message, cause);
	}
}
