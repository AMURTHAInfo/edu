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

/**
 * @author Ninganna.c
 * User account related operations
 */
@Repository
public class LoginDaoImpl extends AbstractGenericDAOHibernate<String, String> implements LoginDaoInterface {
	
	private static final Logger LOGGER = Logger.getLogger(LoginDaoImpl.class);

	@SuppressWarnings("unchecked")
	public Boolean userLoginCheck(String loginId, String password) throws SimsException {
		LOGGER.info("In LoginDaoImpl : userLoginCheck");
		try {
			List<User> userList = (List<User>) findByQueryParams(SqlDml.LOGGED_IN_USER, loginId, password);
			if (userList.size() == 0) {
				return false;
			} else {
				return true;
			}
		} catch (SimsException e) {
			throw e;
		} finally {
			AbstractHibernateUtil.closeSession();
		}
	}

}
