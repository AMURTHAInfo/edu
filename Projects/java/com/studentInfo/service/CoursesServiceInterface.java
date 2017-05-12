package com.studentInfo.service;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;

import com.studentInfo.common.SimsException;
import com.studentInfo.entity.StudentInfo;
import com.studentInfo.model.Message;
import com.studentInfo.model.StudentInfoModel;

/**
 * @author sanjay.kumar
 *
 */
public interface CoursesServiceInterface {

	/**
	 * @param course
	 * @return
	 * @throws SimsException
	 */
	public Message addCourse(StudentInfoModel Course) throws SimsException;

	/**
	 * @return
	 * @throws SimsException
	 */
	public List<StudentInfo> getAllCourses() throws SimsException;

	/**
	 * @param studentInfo
	 * @throws SimsException
	 */
	public Message updateCourse(StudentInfoModel studentInfo) throws SimsException;

	/**
	 * @param studentInfo
	 * @throws SimsException
	 */
	public Message deleteCourse(StudentInfoModel studentInfo) throws SimsException;

	/**
	 * @param studentId
	 * @throws SimsException
	 */
	public StudentInfo getStudentInfoIndi(String studentId) throws SimsException;
}
