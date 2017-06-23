package com.simsservice.entity;

// default package
// Generated Mar 15, 2017 1:00:28 PM by Hibernate Tools 5.2.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Ninganna.c
 *
 */
@Entity
@Table(name = "designation", catalog = "sims_service", uniqueConstraints = { @UniqueConstraint(columnNames = "designation_code") })
public class Designation implements java.io.Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private String designationCode;
	private String designationName;
	
	public Designation() {
	}

	@Id
	@Column(name = "designation_code", unique = true, nullable = false)
	public String getDesignationCode() {
		return designationCode;
	}

	public void setDesignationCode(String designationCode) {
		this.designationCode = designationCode;
	}

	@Column(name = "designation_name", unique = true, nullable = false, length = 11)
	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	@Override
	public String toString() {
		return "Designation [designationCode=" + designationCode
				+ ", designationName=" + designationName + "]";
	}

}
