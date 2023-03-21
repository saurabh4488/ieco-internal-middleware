package ieco.internal.middleware.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * The type Ieco Runtime exception.
 *
 * @author AlokeT
 */
public class IecoRuntimeException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(IecoRuntimeException.class);

	/**
	 * Instantiates a new Ieco Runtime exception.
	 *
	 * @param message the message
	 * @param cause   the cause
	 */
	public IecoRuntimeException(String message, Throwable cause) {
		super(message, cause);
		log.error(message, cause);
	}

	/**
	 * Instantiates a new Ieco Runtime exception.
	 *
	 * @param message the message
	 */
	public IecoRuntimeException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new Ieco Runtime exception.
	 *
	 * @param cause the cause
	 */
	public IecoRuntimeException(Throwable cause) {
		super(cause);
	}
}
