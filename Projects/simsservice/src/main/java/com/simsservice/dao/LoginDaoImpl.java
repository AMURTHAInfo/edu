/**
 * @ (#) LoginDaoImpl.java
 * Project     : SIMS
 * File        : LoginDaoImpl.java
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

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.simsservice.common.AbstractGenericDAOHibernate;
import com.simsservice.common.AbstractHibernateUtil;
import com.simsservice.common.SimsException;
import com.simsservice.common.SqlDml;
import com.simsservice.entity.User;
import com.simsservice.model.UserModel;

/**
 * @author Ninganna.c
 * User account related operations
 */
@Repository
public class LoginDaoImpl extends AbstractGenericDAOHibernate<String, String> implements LoginDaoInterface {
	
	private static final Logger LOGGER = Logger.getLogger(LoginDaoImpl.class);

	@SuppressWarnings("unchecked")
	public UserModel userLoginCheck(String loginId, String password) throws SimsException {
		LOGGER.info("In LoginDaoImpl : userLoginCheck");
		User user;
		List<User> userList;
		UserModel userModel=new UserModel();
		try {
			 userList = (List<User>) findByQueryParams(SqlDml.LOGGED_IN_USER, loginId, password);
		} catch (SimsException e) {
			userModel.setUserStatus(false);
			userModel.setServiceStaus(e.toString());
			return userModel;
		} finally {
			AbstractHibernateUtil.closeSession();
		}
		
		if (userList.size() == 0) {
			userModel.setUserStatus(false);
			userModel.setServiceStaus("No users found By this ID and Password");
			return userModel;
		} else {
			user=userList.get(0);
			if(user.getStatus()=="Y"){
				userModel.setFirstName(user.getFirstName());
				userModel.setLastName(user.getLastName());
				userModel.setUserStatus(true);
				return userModel;
			}
			else{
				userModel.setUserStatus(false);
				userModel.setServiceStaus("User account is deactivated !!");
				return userModel;
			}
		}
	}

}
