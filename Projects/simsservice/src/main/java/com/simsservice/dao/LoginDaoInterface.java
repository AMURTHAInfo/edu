/**
 * @ (#) LoginDaoInterface.java
 * Project     : SIMS
 * File        : LoginDaoInterface.java
 * Author      : Ninganna.c
 * Company     : 
 * Date Created: 13/Mar/2017
 *
 * ========================================================================================================================
 *  No | Modified date |      Modified by     |  Reason
 * ========================================================================================================================
 *  1.   
 * ========================================================================================================================
 */
package com.simsservice.dao;

import com.simsservice.common.SimsException;

/**
 * @author Ninganna.c
 * User account related operations
 */
public interface LoginDaoInterface {

	/**
	 * To check for the valid loginId and password
	 * @param loginId
	 * @param password
	 * @return
	 * @throws SimsException
	 */
	public Boolean userLoginCheck(String loginId, String password)throws SimsException;

}
