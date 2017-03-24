/**
 * @ (#) UserWsInterface.java
 * Project     : SIMS
 * File        : UserWsInterface.java
 * Author      : ninganna.c
 * Company     : 
 * Date Created: 23/Mar/2017
 *
 * ========================================================================================================================
 *  No | Modified date |      Modified by     |  Reason
 * ========================================================================================================================
 *  1.   
 * ========================================================================================================================
 */
package com.simsui.webservice;

import com.simsui.common.SimsException;
import com.simsui.model.UserModel;

/**
 * @author Ninganna.c
 *
 */
public interface UserWsInterface {
	
	/**
	 * @param loginId
	 * @param password
	 * @return
	 * @throws SimsException
	 */
	public UserModel getUser(String loginId, String password) throws SimsException;
}
