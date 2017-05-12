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
package com.studentInfo.common;


/**
 * HQL queries for all the entities hibernate operations
 * 
 * @author sanjay.kumar
 *
 */
public class SqlDml {

	//Courses quieries
	public static final String COURSES_SELECT_QUERY="FROM StudentInfo";
		
	//Employee quieries
	public static final String EMPLOYEES_SELECT_QUERY="FROM Employee";
	public static final String DELETE_EMPLOYEE="DELETE FROM Employee e WHERE e.employeeId = ?";
	
	
	private SqlDml() {
	}
}
