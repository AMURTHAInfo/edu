package com.studentInfo.dao;

import java.util.List;

import com.studentInfo.common.SimsException;
import com.studentInfo.entity.StudentInfo;
import com.studentInfo.model.Message;
import com.studentInfo.model.StudentInfoModel;

public interface CoursesDaoInterface {

	/**
	 * @param course
	 * @return
	 * @throws SimsException
	 */
	public Message addCourse(StudentInfo Course) throws SimsException;

	/**
	 * @return
	 * @throws SimsException
	 */
	public List<StudentInfo> getAllCourses() throws SimsException;
	
	/**
	 * @param studentInfo
	 * @throws SimsException
	 */
	public Message updateCourse(StudentInfo studentInfo) throws SimsException;
	
	/**
	 * @param studentInfo
	 * @throws SimsException
	 */
	public Message deleteCourse(StudentInfo studentInfo) throws SimsException;
	
	/**
	 * @param studentId
	 * @throws SimsException
	 */
	public StudentInfo getStudentInfoIndi(String studentId) throws SimsException;
}
