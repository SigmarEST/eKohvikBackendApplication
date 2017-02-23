package com.coffemachine.module;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "purchase")
public class Purchase implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2911273040380221739L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long purchaseId;
	
	@NotNull
	private LocalDateTime dateTime = LocalDateTime.now();
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="userId")
	private User user;

	private double cost;

	public Purchase(){
		super();
	}

	public Purchase(LocalDateTime date, User user, double cost) {
		super();
		this.dateTime = date;
		this.user = user;
		this.cost = cost;
	}
	
	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public double getCost(){
		return this.cost;
	}

	public void setCost(double cost){
		this.cost = cost;
	}
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
