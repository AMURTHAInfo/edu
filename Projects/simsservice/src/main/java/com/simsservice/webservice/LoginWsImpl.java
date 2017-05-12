/**
 * @ (#) LoginWsImpl.java
 * Project     : SIMS
 * File        : LoginWsImpl.java
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

import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.simsservice.common.RestServicesException;
import com.simsservice.common.SimsException;
import com.simsservice.entity.Designation;
import com.simsservice.entity.Employee;
import com.simsservice.model.ServiceStatus;
import com.simsservice.model.UserModel;
import com.simsservice.service.LoginServiceInterface;

/**
 * 
 * @author Ninganna.c
 * User account related operations
 */
public class LoginWsImpl implements LoginWsInterface {

	private static final Logger LOGGER = Logger.getLogger(LoginWsImpl.class);
	@Autowired
	LoginServiceInterface loginServiceInterface;
	
	/**
	 * To Check validity of the login ID and Password details
	 * 
	 * @return
	 */
	public Response userLoginCheck(String loginId, String password) {
		LOGGER.info("In LoginWsImpl : userLoginCheck");
		try {
			UserModel userModel = loginServiceInterface.userLoginCheck(loginId, password);
			GenericEntity<UserModel> entity = new GenericEntity<UserModel>(userModel) {};
			return Response.ok().entity(entity).build();
		} catch (SimsException e) {
			LOGGER.info("context", e);
			return Response.status(Response.Status.EXPECTATION_FAILED)
					.entity(new RestServicesException(e.getErrorCode(), e.getMessage())).build();
		}
	}
	
	/**
	 * to save the candidate information to database
	 * 
	 * @return
	 * @throws SimsException
	 */
	public Response addEmployee(Employee employee) {
		LOGGER.info("In LoginWsImpl : addEmployee");
		try { 
			Employee employeeObj = loginServiceInterface.addEmployee(employee);
			GenericEntity<Employee> entity = new GenericEntity<Employee>(employeeObj) {
			};
			return Response.ok().entity(entity).build();
		} catch (SimsException e) {
			LOGGER.info("context", e);
			return Response.status(Response.Status.EXPECTATION_FAILED)
					.entity(new RestServicesException(e.getErrorCode(), e.getMessage())).build();
		}
	}

	/**
	 * to get all the employee list
	 * 
	 * @return
	 * @throws SimsException
	 */
	public Response getAllEmployees(Long loggedInUserId) {
		LOGGER.info("In LoginWsImpl : getAllEmployees");
		try {
			List<Employee> employees = loginServiceInterface.getAllEmployees();
			GenericEntity<List<Employee>> entity = new GenericEntity<List<Employee>>(employees) {};
			return Response.ok().entity(entity).build();
		} catch (SimsException e) {
			LOGGER.info("context", e);
			return Response.status(Response.Status.EXPECTATION_FAILED)
					.entity(new RestServicesException(e.getErrorCode(), e.getMessage())).build();
		}
	}

	/**
	 * to delete employee by employeeid
	 * 
	 * @return
	 * @throws SimsException
	 */
	public Response deleteEmployee(Long employeeId) {
		LOGGER.info("In LoginWsImpl : addEmployee");
		if(employeeId != null){
			try { 
				ServiceStatus serviceStatus = loginServiceInterface.deleteEmployee(employeeId);
				GenericEntity<ServiceStatus> entity = new GenericEntity<ServiceStatus>(serviceStatus) {
				};
				return Response.ok().entity(entity).build();
			} catch (SimsException e) {
				LOGGER.info("context", e);
				return Response.status(Response.Status.EXPECTATION_FAILED)
						.entity(new RestServicesException(e.getErrorCode(), e.getMessage())).build();
			}
		}
		else{
			ServiceStatus serviceStatus=new ServiceStatus();
			serviceStatus.setServiceStatus(false);
			serviceStatus.setServiceStausText("Employee Id is null");
			GenericEntity<ServiceStatus> entity = new GenericEntity<ServiceStatus>(serviceStatus) {
			};
			return Response.ok().entity(entity).build();
		}
	}

}
