/**
 * 
 */
package com.simsui.webservice;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.simsui.common.FileOperations;
import com.simsui.common.SimsException;
import com.simsui.model.UserModel;
import com.simsui.service.UserServiceImpl;

/**
 * @author Ninganna.c
 *
 */
public class UserWsImpl extends WSImpl implements UserWsInterface {

	private static final Logger LOGGER = Logger.getLogger(UserWsImpl.class);
	
	@Override
	public UserModel getUser(String loginId, String password) throws SimsException {
		LOGGER.info("In UserWsImpl : getUser");
		List<Object> providers = new ArrayList<Object>();
		providers.add(new JacksonJaxbJsonProvider());
		WebClient client = WebClient.create(FileOperations.getServicePath("userLoginCheck"),providers);
		//client.path("/addCompany");
		client.accept(MediaType.APPLICATION_JSON);
		client.type("application/json");
		/*client.query("loginId", loginId);
		client.query("password", password);*/
		UserModel UserModel=new UserModel();
		UserModel.setLoginId(loginId);
		UserModel.setPassword(password);
		Response response = client.post(UserModel);
		System.out.println("response"+response);
		return handleResponce(UserModel.class, response);
	}

}
