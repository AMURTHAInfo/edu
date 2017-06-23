/**
 * @ (#) DesignationWsImpl.java
 * Project     : SIMS
 * File        : DesignationWsImpl.java
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

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.simsservice.common.RestServicesException;
import com.simsservice.common.SimsException;
import com.simsservice.model.ServiceStatus;
import com.simsservice.service.DesignationServiceInterface;
import com.simsservice.entity.Designation;
/**
 * @author Ninganna.c
 *
 */
public class DesignationWsImpl implements DesignationWsInterface{

	private static final Logger LOGGER = Logger.getLogger(LoginWsImpl.class);
	@Autowired
	private DesignationServiceInterface DesignationServiceInterface;
	
	public Response getAllDesignations(Long loggedInUserId) {
		LOGGER.info("In DesignationWsImpl : getAllDesignations");
		try {
			List<Designation> designation = DesignationServiceInterface.getAllDesignations();
			GenericEntity<List<Designation>> entity = new GenericEntity<List<Designation>>(designation) {};
			return Response.ok().entity(entity).build();
		} catch (SimsException e) {
			LOGGER.info("context", e);
			return Response.status(Response.Status.EXPECTATION_FAILED)
					.entity(new RestServicesException(e.getErrorCode(), e.getMessage())).build();
		}
	}

	public Response addAllDesignations(List<Designation> designationList) {
		LOGGER.info("In DesignationWsImpl : addAllDesignations");
		try {
			ServiceStatus serviceStatus = DesignationServiceInterface.addAllDesignations(designationList);
			GenericEntity<ServiceStatus> entity = new GenericEntity<ServiceStatus>(serviceStatus) {};
			return Response.ok().entity(entity).build();
		} catch (SimsException e) {
			LOGGER.info("context", e);
			return Response.status(Response.Status.EXPECTATION_FAILED)
					.entity(new RestServicesException(e.getErrorCode(), e.getMessage())).build();
		}
	}
	
	
}
