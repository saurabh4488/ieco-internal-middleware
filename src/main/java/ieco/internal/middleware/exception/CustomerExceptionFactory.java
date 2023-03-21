package ieco.internal.middleware.exception;

/**
 * The type Customer exception factory.
 */
public final class CustomerExceptionFactory {

	/**
	 * Instantiates a new customer exception factory.
	 */
	private CustomerExceptionFactory() {
		//not called
	}


	/**
	 * Sync business exception customer exception.
	 *
	 * @param errorCode the error code
	 * @param message   the message
	 * @return the customer exception
	 */
	public static CustomerException syncBusinessException(CustomerErrorCode errorCode, String message) {
		return new InternalBusinessException(errorCode, message);
	}


	/**
	 * Sync technical exception customer exception.
	 *
	 * @param errorCode the error code
	 * @param message   the message
	 * @return the customer exception
	 */
	public static CustomerException syncTechnicalException(CustomerErrorCode errorCode, String message) {
		return new InternalTechnicalException(errorCode, message);
	}


	/**
	 * Sync business exception customer exception.
	 *
	 * @param errorCode the error code
	 * @param message   the message
	 * @param e         the e
	 * @return the customer exception
	 */
	public static CustomerException syncBusinessException(CustomerErrorCode errorCode, String message, Exception e) {
		return new InternalBusinessException(errorCode, message,e);
	}


	/**
	 * Sync technical exception customer exception.
	 *
	 * @param errorCode the error code
	 * @param message   the message
	 * @param e         the e
	 * @return the customer exception
	 */
	public static CustomerException syncTechnicalException(CustomerErrorCode errorCode, String message, Exception e) {
		return new InternalTechnicalException(errorCode, message,e);
	}


}
