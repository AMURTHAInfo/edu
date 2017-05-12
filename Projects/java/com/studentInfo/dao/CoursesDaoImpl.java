package com.studentInfo.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.studentInfo.common.AbstractGenericDAOHibernate;
import com.studentInfo.common.AbstractHibernateUtil;
import com.studentInfo.common.SimsException;
import com.studentInfo.common.SqlDml;
import com.studentInfo.entity.StudentInfo;
import com.studentInfo.model.Message;

@Repository
public class CoursesDaoImpl extends AbstractGenericDAOHibernate<StudentInfo, Long> implements CoursesDaoInterface {

	private static final Logger LOGGER = Logger.getLogger(CoursesDaoImpl.class);

	@Override
	public Message addCourse(StudentInfo Course) throws SimsException {
		LOGGER.info("In CoursesDaoImpl : addCourse");

		StudentInfo studentInModel = new StudentInfo();
		Message msg = new Message();
		boolean status = false;

		try {
			AbstractHibernateUtil.createSession(sessionFactory);
			AbstractHibernateUtil.beginTransaction();
			studentInModel = saveOrUpdate(Course);
			AbstractHibernateUtil.commit();
			status = true;
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

		if (status) {
			msg.setMsg("Successfully added");
			return msg;
		} else {
			msg.setMsg("Failed to add");
			return msg;
		}
	}

	@Override
	public List<StudentInfo> getAllCourses() throws SimsException {
		LOGGER.info("In CoursesDaoImpl : getAllCourses");
		try {
			@SuppressWarnings("unchecked")
			List<StudentInfo> allCourses = findByQuery(SqlDml.COURSES_SELECT_QUERY);
			return allCourses;
		} catch (SimsException e) {
			throw e;
		} finally {
			AbstractHibernateUtil.closeSession();
		}
	}

	@Override
	public Message updateCourse(StudentInfo studentInfo) throws SimsException {
		LOGGER.info("In CoursesDaoImpl : updateCourse");
		return addCourse(studentInfo);
	}

	@Override
	public Message deleteCourse(StudentInfo studentInfo) throws SimsException {
		LOGGER.info("In CoursesDaoImpl : deleteCourse");
		Message msg = new Message();
		boolean status = false;

		try {
			AbstractHibernateUtil.createSession(sessionFactory);
			AbstractHibernateUtil.beginTransaction();
			delete(studentInfo);
			AbstractHibernateUtil.commit();
			status = true;
		} catch (ConstraintViolationException cve) {
			LOGGER.error("Error in delete", cve);
			throw new SimsException(cve.getMessage(), cve.getErrorCode(), cve);
		} catch (JDBCException he) {
			LOGGER.error("Error in delete", he);
			throw new SimsException(he.getMessage(), he.getErrorCode(), he);
		} catch (SimsException e) {
			AbstractHibernateUtil.rollback();
			throw e;
		} finally {
			AbstractHibernateUtil.endTransaction();
			AbstractHibernateUtil.closeSession();
		}

		if (status) {
			msg.setMsg("Successfully deleted");
			return msg;
		} else {
			msg.setMsg("Failed to add");
			return msg;
		}
	}

	@Override
	public StudentInfo getStudentInfoIndi(String studentId) throws SimsException {
		LOGGER.info("In CoursesDaoImpl : deleteCourse");
		StudentInfo studentData = new StudentInfo();

		try {
			AbstractHibernateUtil.createSession(sessionFactory);
			if(!studentId.equals("")){
				studentData = findById(Long.valueOf(studentId));
			}
		} catch (ConstraintViolationException cve) {
			LOGGER.error("Error in delete", cve);
			throw new SimsException(cve.getMessage(), cve.getErrorCode(), cve);
		} catch (JDBCException he) {
			LOGGER.error("Error in delete", he);
			throw new SimsException(he.getMessage(), he.getErrorCode(), he);
		} catch (SimsException e) {
			throw e;
		} finally {
			AbstractHibernateUtil.closeSession();
		}
		return studentData;
	}

}
