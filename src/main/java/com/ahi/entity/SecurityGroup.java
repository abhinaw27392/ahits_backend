package com.ahi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ahi_security_group")
public class SecurityGroup {

	@Id
	@Column(name = "security_group_id")
	private Long id;

	@Column(name = "security_group_name")
	private String name;
	
	@Column(name = "security_group_code")
	private String code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "SecurityGroup [id=" + id + ", name=" + name + ", code=" + code + "]";
	}
	
}
