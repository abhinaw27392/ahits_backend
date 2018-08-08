package com.ahi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ahi_tasks")
public class AhiTasks {

	@Id
	@Column(name = "task_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer taskId;

	@Column(name = "task_name")
	private String taskName;

	@Column(name = "task_description")
	private String taskDescription;

	@ManyToOne(optional = true)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private AhiUser user;

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public AhiUser getUser() {
		return user;
	}

	public void setUser(AhiUser user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (getTaskId() != null)
			result = prime * result + (int) (getTaskId() ^ (getTaskId() >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AhiTasks))
			return false;
		AhiTasks other = (AhiTasks) obj;
		if (getTaskId() != other.getTaskId())
			return false;
		return true;
	}
}
