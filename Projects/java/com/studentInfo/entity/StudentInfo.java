package com.studentInfo.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "student_information", catalog = "student_info", uniqueConstraints = { @UniqueConstraint(columnNames = "student_id")})
public class StudentInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1440805242796620182L;

	private Long studentId;
	private String name;
	public String gender;
	private String branch;
	private String college;
	private String course;
	private String skills;

	public StudentInfo() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "student_id", unique = true, nullable = false)
	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "branch", nullable = false, length = 45)
	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	@Column(name = "gender", nullable = false, length = 45)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "college", nullable = false, length = 45)
	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	@Column(name = "course", unique = true, nullable = false, length = 45)
	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	@Column(name = "skills", nullable = true, length = 45)
	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}
}
