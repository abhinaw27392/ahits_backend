package com.ahi.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ahi_user")
public class AhiUser {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "dob")
	private Date dob;

	@Column(name = "designation")
	private String designation;

	@Column(name = "joining_date")
	private Date joiningDate;

	@Column(name = "role")
	private String role;

//	@ManyToOne
//	@JoinColumn(name = "supervisor_id") 
//	private AhiUser supervisorId;
	
	@Column(name = "supervisor_id")
	private String supervisorId;

	@Column(name = "login_id")
	private String loginId;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "active")
	private boolean active;

	@Column(name = "who_created")
	private String whoCreated;

	@Column(name = "when_created")
	private Date whenCreated;

	@Column(name = "who_updated")
	private String whoUpdated;

	@Column(name = "when_updated")
	private Date whenUpdated;

	@OneToMany
	@JoinTable(name = "ahi_user_security_group", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "user_id") }, inverseJoinColumns = {
					@JoinColumn(name = "security_group_id", referencedColumnName = "security_group_id") })
	private List<SecurityGroup> groups;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDob() {
		return dob;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDesignation() {
		return designation;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getSupervisorId() {
		return supervisorId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWhoCreated() {
		return whoCreated;
	}

	public void setWhoCreated(String whoCreated) {
		this.whoCreated = whoCreated;
	}

	public Date getWhenCreated() {
		return whenCreated;
	}

	public void setWhenCreated(Date whenCreated) {
		this.whenCreated = whenCreated;
	}

	public String getWhoUpdated() {
		return whoUpdated;
	}

	public void setWhoUpdated(String whoUpdated) {
		this.whoUpdated = whoUpdated;
	}

	public Date getWhenUpdated() {
		return whenUpdated;
	}

	public void setWhenUpdated(Date whenUpdated) {
		this.whenUpdated = whenUpdated;
	}

	public List<SecurityGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<SecurityGroup> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return "TycheUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ",dob=" + dob
				+ ",designation=" + designation + ",joiningDate=" + joiningDate + ",role=" + role + ",supervisor="
				+ supervisorId + ", loginId=" + loginId + ", password=" + password + ", active=" + active + ", email="
				+ email + ", whoCreated=" + whoCreated + ", whenCreated=" + whenCreated + ", whoUpdated=" + whoUpdated
				+ ", whenUpdated=" + whenUpdated + "]";
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
		if (!(obj instanceof AhiUser))
			return false;
		AhiUser other = (AhiUser) obj;
		if (getId() != other.getId())
			return false;
		return true;
	}

}
