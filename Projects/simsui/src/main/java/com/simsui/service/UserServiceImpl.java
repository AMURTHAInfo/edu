/**
 * @ (#) UserServiceImpl.java
 * Project     : SIMS
 * File        : UserServiceImpl.java
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

import org.springframework.stereotype.Service;

import com.simsui.common.SimsException;
import com.simsui.model.UserModel;

/**
 * @author Ninganna.c
 *
 */
@Service
public class UserServiceImpl implements UserServiceInterface {

	/* (non-Javadoc)
	 * @see com.simsui.service.UserServiceInterface#addUser(com.smisui.model.UserModel)
	 */
	@Override
	public Boolean addUser(UserModel user) throws SimsException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.simsui.service.UserServiceInterface#getUserByEmail(java.lang.String)
	 */
	@Override
	public UserModel getUserByEmail(String email) throws SimsException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.simsui.service.UserServiceInterface#getUser(java.lang.String, java.lang.String)
	 */
	@Override
	public UserModel getUser(String loginId, String password) throws SimsException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.simsui.service.UserServiceInterface#getUserByloginId()
	 */
	@Override
	public List getUserByloginId() throws SimsException {
		// TODO Auto-generated method stub
		return null;
	}

}
