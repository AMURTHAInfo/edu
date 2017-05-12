/**
 * @ (#) LoginServiceInterface.java
 * Project     : SIMS
 * File        : LoginServiceInterface.java
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
package com.simsservice.service;

import java.util.List;

import com.simsservice.common.SimsException;
import com.simsservice.entity.Employee;
import com.simsservice.model.ServiceStatus;
import com.simsservice.model.UserModel;

/**
 * @author Ninganna.c
 * User account related operations
 */
public interface LoginServiceInterface {
	
	/**
	 * @param loginId
	 * @param password
	 * @return
	 * @throws SimsException
	 */
	public UserModel userLoginCheck(String loginId, String password)throws SimsException;

	/**
	 * @param loginId
	 * @param password
	 * @return
	 * @throws SimsException
	 */
	public Employee addEmployee(Employee employee) throws SimsException;

	/**
	 * @return
	 * @throws SimsException
	 */
	public List<Employee> getAllEmployees() throws SimsException;

	/**
	 * @param employeeId
	 * @return
	 * @throws SimsException
	 */
	public ServiceStatus deleteEmployee(Long employeeId) throws SimsException;
}
