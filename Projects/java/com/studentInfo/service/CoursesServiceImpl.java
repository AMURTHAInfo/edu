package com.studentInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentInfo.common.SimsException;
import com.studentInfo.dao.CoursesDaoInterface;
import com.studentInfo.entity.StudentInfo;
import com.studentInfo.model.Message;
import com.studentInfo.model.StudentInfoModel;

/**
 * @author sanjay.kumar
 *
 */
@Service
public class CoursesServiceImpl implements CoursesServiceInterface {

	@Autowired
	private CoursesDaoInterface courseDao;

	@Override
	public Message addCourse(StudentInfoModel Course) throws SimsException {

		StudentInfo studentInfo = new StudentInfo();
		studentInfo.setName(Course.getName());
		studentInfo.setGender(Course.getGender());
		studentInfo.setBranch(Course.getBranch());
		studentInfo.setCollege(Course.getCollege());
		studentInfo.setCourse(Course.getCourse());
		studentInfo.setSkills(Course.getSkills());
		Message msg = courseDao.addCourse(studentInfo);

		return msg;
	}

	@Override
	public List<StudentInfo> getAllCourses() throws SimsException {
		// TODO Auto-generated method stub
		return courseDao.getAllCourses();
	}

	@Override
	public Message updateCourse(StudentInfoModel studentInfo) throws SimsException {

		StudentInfo studentInfoEdit = new StudentInfo();
		studentInfoEdit.setStudentId(studentInfo.getStudentId());
		studentInfoEdit.setName(studentInfo.getName());
		studentInfoEdit.setGender(studentInfo.getGender());
		studentInfoEdit.setBranch(studentInfo.getBranch());
		studentInfoEdit.setCollege(studentInfo.getCollege());
		studentInfoEdit.setCourse(studentInfo.getCourse());
		studentInfoEdit.setSkills(studentInfo.getSkills());
		Message msg = courseDao.updateCourse(studentInfoEdit);

		return msg;
	}

	@Override
	public Message deleteCourse(StudentInfoModel studentInfo) throws SimsException {
		StudentInfo studentInfodelete = new StudentInfo();
		studentInfodelete.setStudentId(studentInfo.getStudentId());
		Message msg = courseDao.deleteCourse(studentInfodelete);

		return msg;
	}

	@Override
	public StudentInfo getStudentInfoIndi(String studentId) throws SimsException {
		StudentInfo studentData = courseDao.getStudentInfoIndi(studentId);
		return studentData;
	}
}
