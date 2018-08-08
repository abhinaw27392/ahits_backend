package com.ahi.model;

import java.io.Serializable;
import java.util.Date;

public class TimesheetModel implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private Integer empId;
  private String projectName;
  private String taskName;
  private Date date;
  private Integer totalHours;

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
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
}

