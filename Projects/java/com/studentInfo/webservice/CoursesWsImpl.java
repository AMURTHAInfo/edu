package com.studentInfo.webservice;

import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.studentInfo.common.RestServicesException;
import com.studentInfo.common.SimsException;
import com.studentInfo.entity.StudentInfo;
import com.studentInfo.model.Message;
import com.studentInfo.model.StudentInfoModel;
import com.studentInfo.service.CoursesServiceInterface;

public class CoursesWsImpl implements CoursesWsInterface {

	private static final Logger LOGGER = Logger.getLogger(CoursesWsImpl.class);

	@Autowired
	private CoursesServiceInterface courseService;

	@Override
	public Response addCourse(StudentInfoModel course) throws SimsException {
		LOGGER.info("In CourseWsImpl : addCourse" + course);
		Message msg = courseService.addCourse(course);
		return Response.ok().entity(msg).build();
	}

	@Override
	public Response updateCourse(StudentInfoModel course) throws SimsException{
		LOGGER.info("In CourseWsImpl : updateCourse" + course);
		Message msg = courseService.updateCourse(course);
		return Response.ok().entity(msg).build();
	}

	@Override
	public Response deleteCourse(StudentInfoModel course) throws SimsException{
		LOGGER.info("In CourseWsImpl : deleteCourse" + course);
		Message msg = courseService.deleteCourse(course);
		return Response.ok().entity(msg).build();
	}

	@Override
	public Response getStudentInfoIndi(String studentId) throws SimsException {
		LOGGER.info("In CourseWsImpl : getStudentInfoIndi" + studentId);
		StudentInfo studentData = courseService.getStudentInfoIndi(studentId);
		return Response.ok().entity(studentData).build();
	}

	@Override
	public Response getAllCourse() {
		LOGGER.info("In CourseWsImpl : getCourses");
		try {
			List<StudentInfo> studentInformation = courseService.getAllCourses();
			GenericEntity<List<StudentInfo>> entity = new GenericEntity<List<StudentInfo>>(studentInformation) {};
			return Response.ok().entity(entity).build();
		} catch (SimsException e) {
			LOGGER.info("context", e);
			return Response.status(Response.Status.EXPECTATION_FAILED)
					.entity(new RestServicesException(e.getErrorCode(), e.getMessage())).build();
		}
	}

}
