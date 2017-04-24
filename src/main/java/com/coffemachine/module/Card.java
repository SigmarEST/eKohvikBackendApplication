package com.coffemachine.module;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "card")
public class Card implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6093618332067105830L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cardId;
	
	@NotEmpty
	@NotNull
	private String uid;
	
	@NotNull
	@NotEmpty
	private String name;
	
	@NotNull
	private Date createdDate;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="userId")
	private User user;

	
	public Card(){
		super();
	}

	public Card(String uID, String name, Date createdDate, User user) {
		super();
		this.uid = uID;
		this.name = name;
		this.createdDate = createdDate;
		this.user = user;
		
		
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uID) {
		uid = uID;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	


}
