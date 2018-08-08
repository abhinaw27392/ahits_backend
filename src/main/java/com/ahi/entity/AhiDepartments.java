
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
@Table(name = "ahi_departments")
public class AhiDepartments {

	@Id
	@Column(name = "department_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer departmentId;

	@Column(name = "department_name")
	private String departmentName;

	@Column(name = "department_description")
	private String departmentDescription;

	@OneToOne(optional = true)
	@JoinColumn(name = "headed_by_user_id", referencedColumnName = "user_id")
	private AhiUser headedByUser;

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
		if (getDepartmentId() != null)
			result = prime * result + (int) (getDepartmentId() ^ (getDepartmentId() >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AhiDepartments))
			return false;
		AhiDepartments other = (AhiDepartments) obj;
		if (getDepartmentId() != other.getDepartmentId())
			return false;
		return true;
	}
}
