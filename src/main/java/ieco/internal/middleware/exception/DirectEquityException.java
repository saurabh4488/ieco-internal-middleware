package ieco.internal.middleware.exception;

/**
 * The type Customer exception.
 */
public class DirectEquityException extends RuntimeException {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The sync error code.
     */
   final DirectEquityErrorCode syncErrorCode;

    /**
     * Instantiates a new Customer exception.
     *
     * @param syncErrorCode the sync error code
     * @param message       the message
     * @param cause         the cause
     */
    public DirectEquityException(DirectEquityErrorCode syncErrorCode, String message, Throwable cause) {
        super(buildMessage(syncErrorCode, message, cause));
        this.syncErrorCode = syncErrorCode;
    }

    /**
     * Instantiates a new Customer exception.
     *
     * @param syncErrorCode the sync error code
     * @param message       the message
     */
    public DirectEquityException(DirectEquityErrorCode syncErrorCode, String message) {
        super(buildMessage(syncErrorCode, message));
        this.syncErrorCode = syncErrorCode;
    }

    /**
     * Builds the message.
     *
     * @param syncErrorCode the sync error code
     * @param message       the message
     * @return the string
     */
    private static String buildMessage(DirectEquityErrorCode syncErrorCode, String message) {
        return "[" + syncErrorCode.getCode() + "] " + message;
    }

    private static String buildMessage(DirectEquityErrorCode syncErrorCode, String message, Throwable cause) {
        return "[" + syncErrorCode.getCode() + "] " + message + cause.getMessage();
    }

}
