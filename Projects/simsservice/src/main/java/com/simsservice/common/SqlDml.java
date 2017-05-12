/**
 * @ (#) SqlDml.java
 * Project     : SIMS
 * File        : SqlDml.java
 * Author      : Ninganna.c
 * Company     : 
 * Date Created: 15/Mar/2017
 *
 * ========================================================================================================================
 *  No | Modified date |      Modified by     |  Reason
 * ========================================================================================================================
 *  1.   
 * ========================================================================================================================
 */
package com.simsservice.common;

/**
 * HQL queries for all the entities hibernate operations
 * 
 * @author Ninganna.c
 *
 */
public class SqlDml {
	
	// User entity queries
	public static final String LOGGED_IN_USER = "FROM User u  where u.loginId = ? and u.password=?";
	public static final String LOGGED_IN_USER_IDS = "SELECT u.loginId FROM User u";

	//Designation quieries
	public static final String DESIGNATION_SELECT_QUERY="FROM Designation";
		
	//Employee quieries
	public static final String EMPLOYEES_SELECT_QUERY="FROM Employee";
	public static final String DELETE_EMPLOYEE="DELETE FROM Employee e WHERE e.employeeId = ?";
	
	
	private SqlDml() {
	}
}
