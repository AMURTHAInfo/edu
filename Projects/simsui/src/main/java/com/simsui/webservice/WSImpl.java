

/**
 * @ (#) WSImpl.java
 * Project     : SIMS
 * File        : WSImpl.java
 * Author      : Ninganna C
 * Company     : 
 * Date Created: 23/Mar/2017
 *
 * ========================================================================================================================
 *  No | Modified date |      Modfied by     |  Reason
 * ========================================================================================================================
 *  
 * ========================================================================================================================
 */
package com.simsui.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.simsui.common.FileOperations;
import com.simsui.common.RestServicesException;
import com.simsui.common.SimsException;
import com.simsui.model.UserModel;



public class WSImpl {
	private static final Logger LOGGER = Logger.getLogger(WSImpl.class);
	/**
	 * @param c
	 * @param response
	 * @return
	 * @throws SimsException
	 */
	protected <T> T handleResponce(Class<T> entityType, Response response) throws SimsException {
		if (response.getStatus() == 200) {
			return response.readEntity(entityType);
		} else {
			RestServicesException restServicesException = response.readEntity(RestServicesException.class);
			throw new SimsException(restServicesException.getErrorCode(),restServicesException.getErrorMessage());
		}
	}
	
	/**
	 * @param genericType
	 * @param response
	 * @return
	 * @throws SimsException
	 */
	protected <T> T handleResponce(GenericType<T> entityType, Response response) throws SimsException {
		if (response.getStatus() == 200) {
			return response.readEntity(entityType);
		} else {
			RestServicesException restServicesException = response.readEntity(RestServicesException.class);
			throw new SimsException(restServicesException.getErrorCode(),restServicesException.getErrorMessage());
		}
	}
	
	/*protected Response jsonPost(){
		LOGGER.info("In WSImpl : jsonPost");
		List<Object> providers = new ArrayList<Object>();
		providers.add(new JacksonJaxbJsonProvider());
		WebClient client = WebClient.create(FileOperations.getServicePath("userLoginCheck"),providers);
		//client.path("/addCompany");
		client.accept(MediaType.APPLICATION_JSON);
		client.type("application/json");
		client.query("loginId", loginId);
		client.query("password", password);
		UserModel UserModel=new UserModel();
		UserModel.setLoginId(loginId);
		UserModel.setPassword(password);
		Response response = client.post(UserModel);
	}*/
}
