package com.coffemachine.user;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.coffemachine.card.Card;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "user")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="userId")
//@JsonIgnoreProperties({"hibernatelInitializer", "handler"})
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 844259064249982665L;

	@Id
	@Expose
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	@Expose
	@NotNull
	private String name;
	
	@Expose
	@NotNull
	private String email;
	
	@Expose
	@OneToMany(mappedBy = "user", orphanRemoval = true)
	private Set<Card> cards = new HashSet<Card>();

	public User(){
		super();
	};
	

	public User(String name, String email, Set<Card> cards) {
		super();
		this.name = name;
		this.email = email;
		this.cards = cards;
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


	public Set<Card> getCards() {
		return cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}
	
}
