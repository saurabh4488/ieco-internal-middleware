package ieco.internal.middleware.exception;

/**
 * The enum Customer error code.
 */
public enum DirectEquityErrorCode {
    /**
     * Ieco business customer error code.
     */
    IECO_BUSINESS("ERR_IECO_BUS"),
    /**
     * Ieco technical customer error code.
     */
    IECO_TECHNICAL("ERR_IECO_TECH");

    /**
     * The code.
     */
    private String code;

    /**
     * Instantiates a new customer error code.
     *
     * @param code the code
     */
    DirectEquityErrorCode(String code) {
        this.code = code;
    }

    /**
     * Code string.
     *
     * @return the string
     */
    public String getCode() {
        return this.code;
    }

}
