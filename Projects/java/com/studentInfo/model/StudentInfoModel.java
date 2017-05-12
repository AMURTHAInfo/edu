package com.studentInfo.model;

public class StudentInfoModel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 11068635617151367L;

	private Long studentId;
	private String name;
	public String gender;
	private String branch;
	private String college;
	private String course;
	private String skills;

	public StudentInfoModel() {
		// TODO Auto-generated constructor stub
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "StudentInfoModel [studentId=" + studentId + ", name=" + name + ", gender=" + gender + ", branch="
				+ branch + ", college=" + college + ", course=" + course + ", skills=" + skills + "]";
	}
}
