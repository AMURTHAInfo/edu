/**
 * @ (#) DesignationDaoImpl.java
 * Project     : SIMS
 * File        : DesignationDaoImpl.java
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

import java.util.Iterator;
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
import com.simsservice.entity.Employee;
import com.simsservice.model.ServiceStatus;

/**
 * @author Ninganna.c
 *
 */

@Repository
public class DesignationDaoImpl extends AbstractGenericDAOHibernate<Designation, Long> implements DesignationDaoInterfcae {

	private static final Logger LOGGER = Logger.getLogger(DesignationDaoImpl.class);
	
	public List<Designation> getAllDesignations() throws SimsException{
		LOGGER.info("In DesignationDaoImpl : getAllDesignations");
		try {
			List<Designation> designation = findByQuery(SqlDml.DESIGNATION_SELECT_QUERY);
			return designation;
		} catch (SimsException e) {
			throw e;
		} finally {
			AbstractHibernateUtil.closeSession();
		}
	}

	public ServiceStatus addAllDesignations(List<Designation> designationList) throws SimsException {
		LOGGER.info("In DesignationDaoImpl : addAllDesignations");
		Iterator<Designation> itr = designationList.iterator();
		ServiceStatus serviceStatus=new ServiceStatus();
		Designation designationObj =new Designation();
		Boolean status=true;
		while (itr.hasNext()) {
			Designation designation = (Designation) itr.next();
			try {
				AbstractHibernateUtil.createSession(sessionFactory);
				AbstractHibernateUtil.beginTransaction();
				designationObj = (Designation) saveOrUpdate(designation);
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
			if (designationObj == null) {
				status=false;
				break;
			} 
		}
		if(status){
			serviceStatus.setServiceStatus(true);
			return serviceStatus;
		}
		else{
			serviceStatus.setServiceStatus(false);
			return serviceStatus;
		}
	}

}
