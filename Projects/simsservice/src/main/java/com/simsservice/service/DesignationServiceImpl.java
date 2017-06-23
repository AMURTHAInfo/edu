/**
 * @ (#) DesignationServiceImpl.java
 * Project     : SIMS
 * File        : DesignationServiceImpl.java
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
package com.simsservice.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simsservice.common.SimsException;
import com.simsservice.dao.DesignationDaoInterfcae;
import com.simsservice.dao.LoginDaoInterface;
import com.simsservice.entity.Designation;
import com.simsservice.model.ServiceStatus;

/**
 * @author Ninganna.c
 *
 */
@Service
public class DesignationServiceImpl implements DesignationServiceInterface {

	private static final Logger LOGGER = Logger.getLogger(DesignationServiceImpl.class);

	@Autowired
	DesignationDaoInterfcae designationDaoInterface;
	
	public List<Designation> getAllDesignations() throws SimsException{
		LOGGER.info("In DesignationServiceImpl : getAllDesignations");
		return designationDaoInterface.getAllDesignations();
	}

	public ServiceStatus addAllDesignations(List<Designation> designationList) throws SimsException {
		LOGGER.info("In DesignationServiceImpl : addAllDesignations");
		return designationDaoInterface.addAllDesignations(designationList);
	}

}
