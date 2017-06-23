/**
 * @ (#) DesignationServiceInterface.java
 * Project     : SIMS
 * File        : DesignationServiceInterface.java
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

import com.simsservice.common.SimsException;
import com.simsservice.entity.Designation;
import com.simsservice.model.ServiceStatus;

/**
 * @author Ninganna.c
 *
 */
public interface DesignationServiceInterface {

	/**
	 * @return 
	 */
	public List<Designation> getAllDesignations() throws SimsException;
 
	/**
	 * @param designationList
	 * @return
	 * @throws SimsException
	 */
	public ServiceStatus addAllDesignations(List<Designation> designationList) throws SimsException;
}
