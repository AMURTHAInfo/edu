/**
 * @ (#) DesignationDaoInterfcae.java
 * Project     : SIMS
 * File        : DesignationDaoInterfcae.java
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
package com.simsservice.dao;

import java.util.List;

import com.simsservice.common.SimsException;
import com.simsservice.entity.Designation;
import com.simsservice.model.ServiceStatus;

/**
 * @author Ninganna.c
 *
 */
public interface DesignationDaoInterfcae {

	/**
	 * @return
	 */
	public List<Designation> getAllDesignations() throws SimsException;

	/**
	 * @param designationList
	 * @return
	 */
	public ServiceStatus addAllDesignations(List<Designation> designationList)throws SimsException;

}
