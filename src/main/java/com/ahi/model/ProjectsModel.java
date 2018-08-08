package com.ahi.model;

import java.io.Serializable;

public class ProjectsModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer projectId;
	private String projectName;
	private String projectDescription;
	private Integer headedByUserId;
	private String headedBy;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
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
