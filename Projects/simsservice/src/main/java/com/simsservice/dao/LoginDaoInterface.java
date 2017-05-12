/**
 * @ (#) LoginDaoInterface.java
 * Project     : SIMS
 * File        : LoginDaoInterface.java
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
package com.simsservice.dao;

import java.util.List;

import com.simsservice.common.SimsException;
import com.simsservice.model.ServiceStatus;
import com.simsservice.model.UserModel;
import com.simsservice.entity.Employee;

/**
 * @author Ninganna.c
 * User account related operations
 */
public interface LoginDaoInterface {

	/**
	 * To check for the valid loginId and password
	 * @param loginId
	 * @param password
	 * @return
	 * @throws SimsException
	 */
	public UserModel userLoginCheck(String loginId, String password)throws SimsException;

	/**
	 * To check for the valid loginId and password
	 * @param loginId
	 * @param password
	 * @return
	 * @throws SimsException
	 */
	public Employee addEmployee(Employee employee)throws SimsException;

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
