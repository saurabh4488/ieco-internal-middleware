package ieco.internal.middleware.exception;

/**
 * The enum Customer error code.
 */
public enum CustomerErrorCode {
	/**
	 * Ieco business customer error code.
	 */
	IECO_BUSINESS("ERR_IECO_BUS"),
	/**
	 * Ieco technical customer error code.
	 */
	IECO_TECHNICAL("ERR_IECO_TECH"),
	/**
	 * Ieco file storage customer error code.
	 */
	IECO_FILE_STORAGE("ERR_IECO_FILE_STORAGE");

	/** The code. */
	private String code;

	/**
	 * Instantiates a new customer error code.
	 *
	 * @param code the code
	 */
	CustomerErrorCode(String code) {
		this.code = code;
	}

	/**
	 * Code string.
	 *
	 * @return the string
	 */
	public String code() {
		return this.code;
	}

}
