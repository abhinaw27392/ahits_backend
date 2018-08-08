package com.ahi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ahi_projects")
public class AhiProjects {

	@Id
	@Column(name = "project_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer projectId;

	@Column(name = "project_name")
	private String projectName;

	@Column(name = "project_description")
	private String projectDescription;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "headed_by_user_id", referencedColumnName = "user_id")
	private AhiUser headedByUser;



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
	public AhiUser getHeadedByUser() {
		return headedByUser;
	}

	public void setHeadedByUser(AhiUser headedByUser) {
		this.headedByUser = headedByUser;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (getProjectId() != null)
			result = prime * result + (int) (getProjectId() ^ (getProjectId() >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AhiProjects))
			return false;
		AhiProjects other = (AhiProjects) obj;
		if (getProjectId() != other.getProjectId())
			return false;
		return true;
	}
}
