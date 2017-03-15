/**
 * @ (#) RestServicesException.java
 * Project     : SIMS
 * File        : RestServicesException.java
 * Author      : Ninganna.c
 * Company     : 
 * Date Created: 15/Mar/2017
 *
 * ========================================================================================================================
 *  No | Modified date |      Modified by     |  Reason
 * ========================================================================================================================
 *  1.   
 * ========================================================================================================================
 */
package com.simsservice.common;

/**
 * general exception class for SIMS webservices Application.
 * @author Ninganna.c
 *
 */
public class RestServicesException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3680006847392165772L;
	private int errorCode;

	/**
	 * Constructor.
	 */
	public RestServicesException() {
		super();

	}

	/**
	 * constructor.
	 * 
	 * @param errorMessage
	 *            String
	 * @param errorCause
	 *            Throwable
	 */
	public RestServicesException(final String errorMessage, final Throwable errorCause) {
		super(errorMessage, errorCause);

	}

	/**
	 * constructor.
	 * 
	 * @param errorMessage
	 * @param errorCode
	 * @param errorCause
	 */
	public RestServicesException(final String errorMessage, final int errorCode, final Throwable errorCause) {
		super(errorMessage, errorCause);
		this.errorCode = errorCode;
	}

	/**
	 * constructor.
	 * 
	 * @param errorMessage
	 * @param errorCode
	 * @param errorCause
	 */
	public RestServicesException(final int errorCode, final String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
	}

	/**
	 * Constructor.
	 * 
	 * @param errorMessage
	 *            String
	 */
	public RestServicesException(final String errorMessage) {
		super(errorMessage);

	}

	/**
	 * Constructor.
	 * 
	 * @param errorCause
	 *            Throwable
	 */
	public RestServicesException(final Throwable errorCause) {
		super(errorCause);

	}

	/**
	 * getter for error code.
	 * 
	 * @return int
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * setter for error code.
	 * 
	 * @param errorCode
	 *            int
	 */
	public void setErrorCode(final int errorCode) {
		this.errorCode = errorCode;
	}
}
