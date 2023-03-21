package ieco.internal.middleware.exception;

/**
 * The type Customer exception factory.
 */
public final class DirectEquityExceptionFactory {

    /**
     * Instantiates a new customer exception factory.
     */
    private DirectEquityExceptionFactory() {
        //not called
    }

    public static InternalTechnicalException syncTechnicalException(CustomerErrorCode errorCode, String message) {
        return new InternalTechnicalException(errorCode, message);
    }

    public static InternalTechnicalException syncTechnicalException(CustomerErrorCode errorCode, String message, Exception e) {
        return new InternalTechnicalException(errorCode, message, e);
    }


}
