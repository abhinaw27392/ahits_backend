package com.ahi.model;

import java.io.Serializable;

public class DepartmentsModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer departmentId;
	private String departmentName;
	private String departmentDescription;
	private Integer headedByUserId;
	private String headedBy;

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentDescription() {
		return departmentDescription;
	}

	public void setDepartmentDescription(String departmentDescription) {
		this.departmentDescription = departmentDescription;
	}

	public Integer getHeadedByUserId() {
		return headedByUserId;
	}

	public void setHeadedByUserId(Integer headedByUserId) {
		this.headedByUserId = headedByUserId;
	}

	public String getHeadedBy() {
		return headedBy;
	}

	public void setHeadedBy(String headedBy) {
		this.headedBy = headedBy;
	}

}
