package com.coffemachine.item;

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
@Table(name = "item")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="itemId")
@JsonIgnoreProperties({"hibernatelInitializer", "handler"})
public class Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4308379451088353594L;

	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long itemId;
	
	@Expose
	@NotNull
	private String name;
	
	@Expose
	@NotNull
	private double price;
	
	@Expose
	@NotNull
	private boolean available;
	
	//@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "purchaseId")
	//private Purchase purchase;

	public Item(){
		super();
	}
	
	public Item(String name, double price, boolean available) {
		super();
		this.name = name;
		this.price = price;
		this.available = available;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	

}
