package com.coffemachine.card;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.coffemachine.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "card")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="cardId")
//@JsonIgnoreProperties({"hibernatelInitializer", "handler"})
public class Card implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6093618332067105830L;

	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cardId;
	
	@NotEmpty
	@NotNull
	@Expose
	private String uid;
	
	@Expose
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="userId")
	private User user;
	
	public Card(){
		super();
	}

	public Card(String uID, User user) {
		super();
		this.uid = uID;
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


}
