package com.coffemachine.dto;

import java.io.Serializable;
import java.util.Date;

public class CardDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8161883072500893965L;

	private Long cardId;
	
	private String uid;
	
	private String name;

	private String userEmail;
	
	private Date createdDate;
	

	public CardDTO() {
		super();
	}


	public CardDTO(Long cardId, String uid, String name, String userEmail, Date createdDate) {
		super();
		this.cardId = cardId;
		this.uid = uid;
		this.name = name;
		this.userEmail = userEmail;
		this.createdDate = createdDate;
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


	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


}
