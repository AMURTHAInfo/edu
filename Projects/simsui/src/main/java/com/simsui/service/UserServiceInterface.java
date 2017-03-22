/**
 * @ (#) UserServiceInterface.java
 * Project     : SIMS
 * File        : UserServiceInterface.java
 * Author      : ninganna.c
 * Company     : 
 * Date Created: 21/Mar/2017
 *
 * ========================================================================================================================
 *  No | Modified date |      Modified by     |  Reason
 * ========================================================================================================================
 *  1.   
 * ========================================================================================================================
 */
package com.simsui.service;

import java.util.List;

import com.simsui.common.SimsException;
import com.simsui.model.UserModel;

/**
 * @author Ninganna.c
 *
 */
public interface UserServiceInterface {
	/**
	 * @param user
	 * @return
	 * @throws SimsException
	 */
	Boolean addUser(UserModel user) throws SimsException;

	/**
	 * @param email
	 * @return
	 * @throws SimsException
	 */
	UserModel getUserByEmail(String email) throws SimsException;

	/**
	 * @param loginId
	 * @param password
	 * @return
	 * @throws SimsException
	 */
	UserModel getUser(String loginId, String password) throws SimsException;

	/**
	 * @return
	 * @throws SimsException
	 */
	List getUserByloginId() throws SimsException;


}
