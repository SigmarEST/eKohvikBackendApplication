package com.coffemachine.purchase;

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

import com.coffemachine.user.User;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "purchase")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="purchaseId")
//@JsonIgnoreProperties({"hibernatelInitializer", "handler"})
public class Purchase implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2911273040380221739L;
	

	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long purchaseId;
	
	@Expose
	@NotNull
	private LocalDateTime dateTime = LocalDateTime.now();
	
	@Expose
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
