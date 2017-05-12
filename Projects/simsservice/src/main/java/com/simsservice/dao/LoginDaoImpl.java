/**
 * @ (#) LoginDaoImpl.java
 * Project     : SIMS
 * File        : LoginDaoImpl.java
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

import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.simsservice.common.AbstractGenericDAOHibernate;
import com.simsservice.common.AbstractHibernateUtil;
import com.simsservice.common.SimsException;
import com.simsservice.common.SqlDml;
import com.simsservice.entity.Designation;
import com.simsservice.entity.User;
import com.simsservice.model.ServiceStatus;
import com.simsservice.model.UserModel;
import com.simsservice.entity.Employee;

/**
 * @author Ninganna.c
 * User account related operations
 */
@Repository
public class LoginDaoImpl extends AbstractGenericDAOHibernate<Employee, String> implements LoginDaoInterface {
	
	private static final Logger LOGGER = Logger.getLogger(LoginDaoImpl.class);

	@SuppressWarnings("unchecked")
	public UserModel userLoginCheck(String loginId, String password) throws SimsException {
		LOGGER.info("In LoginDaoImpl : userLoginCheck");
		User user;
		List<User> userList;
		UserModel userModel=new UserModel();
		try {
			 userList = (List<User>) findByQueryParams(SqlDml.LOGGED_IN_USER, loginId, password);
		} catch (SimsException e) {
			userModel.setUserStatus(false);
			userModel.setServiceStaus(e.toString());
			return userModel;
		} finally {
			AbstractHibernateUtil.closeSession();
		}
		
		if (userList.size() == 0) {
			userModel.setUserStatus(false);
			userModel.setServiceStaus("No users found By this ID and Password");
			return userModel;
		} else {
			user=userList.get(0);
			if(user.getStatus()=="Y"){
				userModel.setFirstName(user.getFirstName());
				userModel.setLastName(user.getLastName());
				userModel.setUserStatus(true);
				return userModel;
			}
			else{
				userModel.setUserStatus(false);
				userModel.setServiceStaus("User account is deactivated !!");
				return userModel;
			}
		}
	}

	
	/**
	 * to save candidate information to database
	 */ 
	public Employee addEmployee(Employee employee) throws SimsException {
		LOGGER.info("In LoginDaoImpl : addEmployee");
		Employee employeeObj = null;
		try {
			AbstractHibernateUtil.createSession(sessionFactory);
			AbstractHibernateUtil.beginTransaction();
			employeeObj = (Employee) saveOrUpdate(employee);
			AbstractHibernateUtil.commit();
		} catch (ConstraintViolationException cve) {
			LOGGER.error("Error in merge", cve);
			throw new SimsException(cve.getMessage(), cve.getErrorCode(), cve);
		} catch (JDBCException he) {
			LOGGER.error("Error in merge", he);
			throw new SimsException(he.getMessage(), he.getErrorCode(), he);
		} catch (SimsException e) {
			AbstractHibernateUtil.rollback();
			throw e;
		} finally {
			AbstractHibernateUtil.endTransaction();
			AbstractHibernateUtil.closeSession();
		}
		if (employeeObj == null) {
			return employeeObj;
		} else {
			return employeeObj;
		}
	}


	public List<Employee> getAllEmployees() throws SimsException {
		LOGGER.info("In LoginDaoImpl : getAllEmployees");
		try {
			List<Employee> designation = findByQuery(SqlDml.EMPLOYEES_SELECT_QUERY);
			return designation;
		} catch (SimsException e) {
			throw e;
		} finally {
			AbstractHibernateUtil.closeSession();
		}
	}


	public ServiceStatus deleteEmployee(Long employeeId) throws SimsException {
		LOGGER.info("In LoginDaoImpl : deleteEmployee of "+employeeId);
		ServiceStatus serviceStatus=new ServiceStatus();
		try {
			updateOrDeleteQuery(SqlDml.DELETE_EMPLOYEE, Long.toString(employeeId));
			serviceStatus.setServiceStatus(true);
			serviceStatus.setServiceStausText("Sucessfully deleted the Employee");
			return serviceStatus;
		} catch (SimsException e) {
			serviceStatus.setServiceStatus(false);
			serviceStatus.setServiceStausText("Unsucessfull in deleting the Employee");
			return serviceStatus;
		} finally {
			AbstractHibernateUtil.closeSession();
		}
	}
}
