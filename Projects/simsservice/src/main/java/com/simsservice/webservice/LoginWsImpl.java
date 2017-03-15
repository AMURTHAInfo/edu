/**
 * @ (#) LoginWsImpl.java
 * Project     : SIMS
 * File        : LoginWsImpl.java
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
package com.simsservice.webservice;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.simsservice.common.RestServicesException;
import com.simsservice.common.SimsException;
import com.simsservice.service.LoginServiceInterface;

/**
 * 
 * @author Ninganna.c
 * User account related operations
 */
public class LoginWsImpl implements LoginWsInterface {

	private static final Logger LOGGER = Logger.getLogger(LoginWsImpl.class);
	@Autowired
	LoginServiceInterface loginServiceInterface;
	
	/**
	 * To Check validity of the login ID and Password details
	 * 
	 * @return
	 */
	public Response userLoginCheck(String loginId, String password) {
		LOGGER.info("In LoginWsInterface : userLoginCheck");
		try {
			Boolean status = loginServiceInterface.userLoginCheck(loginId, password);
			GenericEntity<Boolean> entity = new GenericEntity<Boolean>(status) {};
			return Response.ok().entity(entity).build();
		} catch (SimsException e) {
			LOGGER.info("context", e);
			return Response.status(Response.Status.EXPECTATION_FAILED)
					.entity(new RestServicesException(e.getErrorCode(), e.getMessage())).build();
		}
	}
}
