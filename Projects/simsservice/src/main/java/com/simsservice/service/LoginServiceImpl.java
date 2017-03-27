/**
 * @ (#) LoginServiceImpl.java
 * Project     : SIMS
 * File        : LoginServiceImpl.java
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
package com.simsservice.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simsservice.common.SimsException;
import com.simsservice.dao.LoginDaoInterface;
import com.simsservice.model.UserModel;

/**
 * @author Ninganna.c
 * User account related operations
 */
@Service
public class LoginServiceImpl implements LoginServiceInterface {
	
	private static final Logger LOGGER = Logger.getLogger(LoginServiceInterface.class);

	@Autowired
	LoginDaoInterface loginDaoInterface;
	
	/**
	 * To check for the valid loginId and password
	 */
	public UserModel userLoginCheck(String loginId, String password) throws SimsException {
		LOGGER.info("TestUserTimeServiceImpl:addTime");
		return loginDaoInterface.userLoginCheck(loginId, password);
	}

}
