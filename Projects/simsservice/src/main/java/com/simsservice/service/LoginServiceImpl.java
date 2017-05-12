/**
 * @ (#) LoginServiceImpl.java
 * Project     : SIMS
 * File        : LoginServiceImpl.java
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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simsservice.common.SimsException;
import com.simsservice.dao.LoginDaoInterface;
import com.simsservice.entity.Employee;
import com.simsservice.model.ServiceStatus;
import com.simsservice.model.UserModel;

/**
 * @author Ninganna.c
 * User account related operations
 */
@Service
public class LoginServiceImpl implements LoginServiceInterface {
	
	private static final Logger LOGGER = Logger.getLogger(LoginServiceInterface.class);

	@Autowired
	LoginDaoInterface loginDaoInterface;
	
	/**
	 * To check for the valid loginId and password
	 */
	public UserModel userLoginCheck(String loginId, String password) throws SimsException {
		LOGGER.info("LoginServiceInterface : userLoginCheck");
		return loginDaoInterface.userLoginCheck(loginId, password);
	}

	/**
	 * To check for the valid loginId and password
	 */
	public Employee addEmployee(Employee employee) throws SimsException {
		LOGGER.info("LoginServiceInterface : addEmployee");
		return loginDaoInterface.addEmployee(employee);
	}

	/**
	 * To get all employee List
	 */
	public List<Employee> getAllEmployees() throws SimsException {
		LOGGER.info("LoginServiceInterface : getAllEmployees");
		return loginDaoInterface.getAllEmployees();
	}

	/**
	 * To delete employee
	 */
	public ServiceStatus deleteEmployee(Long employeeId) throws SimsException {
		LOGGER.info("LoginServiceInterface : deleteEmployee");
		return loginDaoInterface.deleteEmployee(employeeId);
	}
}
