package com.coffemachine.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="itemId")
	private List<Item> items = new ArrayList<Item>();
	
	private double cost;
	
	@NotNull
	private Date dateTime;
	

	public Purchase(){
		super();
	}

	

	public Purchase(Date date, User user, double cost, List<Item> items) {
		super();
		this.dateTime = date;
		this.user = user;
		this.cost = cost;
		this.items = items;
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
	
	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
