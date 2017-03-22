/**
 * @ (#) SimsException.java
 * Project     : SIMS
 * File        : SimsException.java
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
package com.simsui.common;

/**
 * general exception class for SIMS Application.
 * @author Ninganna.c
 */
public class SimsException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8981196832447909302L;
	private int errorCode;

	/**
	 * Constructor.
	 */
	public SimsException() {
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
	public SimsException(final String errorMessage, final Throwable errorCause) {
		super(errorMessage, errorCause);

	}

	/**
	 * constructor.
	 * 
	 * @param errorMessage
	 * @param errorCode
	 * @param errorCause
	 */
	public SimsException(final String errorMessage, final int errorCode, final Throwable errorCause) {
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
	public SimsException(final int errorCode, final String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
	}

	/**
	 * Constructor.
	 * 
	 * @param errorMessage
	 *            String
	 */
	public SimsException(final String errorMessage) {
		super(errorMessage);

	}

	/**
	 * Constructor.
	 * 
	 * @param errorCause
	 *            Throwable
	 */
	public SimsException(final Throwable errorCause) {
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
