/**
 * @ (#) DesignationWsInterface.java
 * Project     : SIMS
 * File        : DesignationWsInterface.java
 * Author      : Ninganna.c
 * Company     : 
 * Date Created: 20/Apr/2017
 *
 * ========================================================================================================================
 *  No | Modified date |      Modified by     |  Reason
 * ========================================================================================================================
 *  1.   
 * ========================================================================================================================
 */
package com.simsservice.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simsservice.entity.*;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * @author Ninganna.c
 *
 */

@Path(value = "/designationWs")
@Api(value = "/designationWs", description = "designation related operations")
public interface DesignationWsInterface {

	/**
	 * To get all set of designations
	 * 
	 * @param loggedInUserId
	 * @return
	 */
	@GET
	@Path("/getAllDesignations")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get all designations", response = Designation.class)
	public @ResponseBody Response getAllDesignations(@QueryParam("loggedInUserId") Long loggedInUserId);
	
	@POST
	@Path("/addAllDesignations")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "save all designations", response = Designation.class)
	public @ResponseBody Response addAllDesignations(@RequestBody List<Designation> designationList);
}
