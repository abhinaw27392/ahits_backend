package com.ahi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ahi_timesheet")
public class AhiTimesheet {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;

	@Column(name = "project_name")
	private String projectName;

	@Column(name = "task_name")
	private String taskName;

	@Column(name = "date")
	private Date date;

	@Column(name = "total_hours")
	private Integer totalHours;

	@Column(name = "emp_id")
	private Integer empId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setTotalHours(Integer totalHours) {
		this.totalHours = totalHours;
	}

	public Integer getTotalHours() {
		return totalHours;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (getId() != null)
			result = prime * result + (int) (getId() ^ (getId() >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AhiTimesheet))
			return false;
		AhiTimesheet other = (AhiTimesheet) obj;
		if (getId() != other.getId())
			return false;
		return true;
	}

}
