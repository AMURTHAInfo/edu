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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simsservice.entity.Designation;
import com.simsservice.entity.Employee;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path(value = "/employeeWs")
@Api(value = "/employeeWs", description = "User account related operations")
public interface LoginWsInterface {

	/**
	 * To check user validity
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
	

	/**
	 * to Save employee details
	 * 
	 * @return
	 */
	@POST
	@Path(value = "/addEmployee")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Save employee details", response = Employee.class)
	public @ResponseBody Response addEmployee(@RequestBody Employee employee);
	
	/**
	 * To get all employees
	 * 
	 * @param loggedInUserId
	 * @return
	 */
	@GET
	@Path("/getAllEmployees")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get all employee lists", response = Employee.class)
	public @ResponseBody Response getAllEmployees(@QueryParam("loggedInUserId") Long loggedInUserId);

	/**
	 * to Save employee details
	 * 
	 * @return
	 */
	@POST
	@Path(value = "/deleteEmployee")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Delete employee details", response = Employee.class)
	public @ResponseBody Response deleteEmployee(@QueryParam("employeeId") Long employeeId);
	
}