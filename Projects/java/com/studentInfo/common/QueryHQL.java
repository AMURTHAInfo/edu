/**
 * @ (#) QueryHQL.java
 * Project     : SIMS
 * File        : QueryHQL.java
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

import java.util.ArrayList;
import java.util.List;

/**
 * for Hql query common operations
 * @author Ninganna.c
 *
 */
public class QueryHQL {
	public List <Object> validParams = new ArrayList<Object>();
	public String dynamicQuery = null;
	
	/**
	 * @return the validParams
	 */
	public List<Object> getValidParams() {
		return validParams;
	}
	/**
	 * @param validParams the validParams to set
	 */
	public void setValidParams(List <Object> validParams) {
		this.validParams = validParams;
	}
	/**
	 * @return the dynamicQuery
	 */
	public String getDynamicQuery() {
		return dynamicQuery;
	}
	/**
	 * @param dynamicQuery the dynamicQuery to set
	 */
	public void setDynamicQuery(String dynamicQuery) {
		this.dynamicQuery = dynamicQuery;
	}
}
