package com.coffemachine.module;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "user")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 844259064249982665L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	@NotNull
	private String name;
	
	@NotNull
	private String email;
	
	private double balance;
	
	private boolean active = true;
	
	private boolean cardAddingBlocked = false;
	
	@NotNull
	private Date createdDate;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Card> cards = new HashSet<Card>();

	public User(){
		super();
	};
	

	public User(String name, String email, double balance, boolean active, boolean cardAddingBlocked, Date createdDate, Set<Card> cards) {
		super();
		this.name = name;
		this.email = email;
		this.balance = balance;
		this.active = active;
		this.cardAddingBlocked = cardAddingBlocked;
		this.createdDate = createdDate;
		this.cards = cards;
	}

	public boolean isCardAddingBlocked() {
		return cardAddingBlocked;
	}


	public void setCardAddingBlocked(boolean cardAddingBlocked) {
		this.cardAddingBlocked = cardAddingBlocked;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	
	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public Set<Card> getCards() {
		return cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
