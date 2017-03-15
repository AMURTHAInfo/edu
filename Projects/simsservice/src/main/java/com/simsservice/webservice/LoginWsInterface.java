/**
 * @ (#) LoginWsInterface.java
 * Project     : SIMS
 * File        : LoginWsInterface.java
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

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path(value = "/loginWs")
@Api(value = "/loginWs", description = "User account related operations")
public interface LoginWsInterface {

	/**
	 * To Save user time
	 * 
	 * @param loginId
	 * @param password
	 * @return
	 */
	@POST
	@Path(value = "/userLoginCheck")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "To check for the valid loginId and password", response = Boolean.class)
	public Response userLoginCheck(@QueryParam("loginId") String loginId, @QueryParam("password") String password);
	
}