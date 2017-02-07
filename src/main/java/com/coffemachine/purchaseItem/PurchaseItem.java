package com.coffemachine.purchaseItem;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.coffemachine.item.Item;
import com.coffemachine.purchase.Purchase;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.gson.annotations.Expose;

@Entity
@Table(name="purchaseItem")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="idPurchaseItem")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PurchaseItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8586798036291697679L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Expose
	private Long purchaseItemId;
	
	@ManyToOne
    @JoinColumn(name="purchaseId")
	private Purchase purchase;
	
	@ManyToOne
    @JoinColumn(name="itemId")
	@Expose
	private Item item;
	
	@NotNull
	@Expose
	private Integer amount;
	
	public PurchaseItem(){
		super();
	}
	
	public PurchaseItem(Long purchaseItemId, Purchase purchase, Item item, Integer amount) {
		super();
		this.purchaseItemId = purchaseItemId;
		this.purchase = purchase;
		this.item = item;
		this.amount = amount;
	}

	public Long getPurchaseItemId() {
		return purchaseItemId;
	}

	public void setPurchaseItemId(Long purchaseItemId) {
		this.purchaseItemId = purchaseItemId;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	

}
