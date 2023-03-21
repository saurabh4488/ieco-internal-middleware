package ieco.internal.middleware.enums;

/**
 * The enum Pattern type.
 */
public enum PatternType {
	/**
	 * Email address pattern type.
	 */
	EMAIL_ADDRESS("email"),

	/** The date. */
	DATE("date");

	/** The value. */
	private String value;

	/**
	 * Instantiates a new pattern type.
	 *
	 * @param value the value
	 */
	PatternType(String value) {
		this.value = value;
	}

	/**
	 * Gets value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
}
