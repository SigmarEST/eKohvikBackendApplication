package com.coffemachine.dto;

import java.io.Serializable;

public class CardDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8161883072500893965L;

	private Long cardId;
	
	private String uid;
	
	private String userEmail;
	

	public CardDTO() {
		super();
	}


	public CardDTO(Long cardId, String uid, String userEmail) {
		super();
		this.cardId = cardId;
		this.uid = uid;
		this.userEmail = userEmail;
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


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}



}
