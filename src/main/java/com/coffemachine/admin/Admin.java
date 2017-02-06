package com.coffemachine.admin;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "admin")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="adminId")
@JsonIgnoreProperties({"hibernatelInitializer", "handler"})
public class Admin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3726853031350311413L;
	
	@Id
	@Expose
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long adminId;
	
	@Expose
	@NotNull
	private String username;
	
	@Expose
	@NotNull
	private String password;
	
	@Expose
	@NotNull
	private String name;
	
	@Expose
	@NotNull
	private String email;
	
	

	public Admin() {
		super();
	}
	
	

	public Admin(String username, String password, String name, String email) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
	}



	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
