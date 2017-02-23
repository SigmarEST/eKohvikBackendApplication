package com.coffemachine.module;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="purchaseItem")
public class PurchaseItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8586798036291697679L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long purchaseItemId;
	
	@ManyToOne
    @JoinColumn(name="purchaseItemLineId")
	private PurchaseItemLine purchaseItemLine;
	
	@ManyToOne
    @JoinColumn(name="itemId")
	private Item item;
	
	@NotNull
	private Integer amount;
	
	public PurchaseItem(){
		super();
	}
	
	public PurchaseItem(Long purchaseItemId, PurchaseItemLine purchaseItemLine, Item item, Integer amount) {
		super();
		this.purchaseItemId = purchaseItemId;
		this.purchaseItemLine = purchaseItemLine;
		this.item = item;
		this.amount = amount;
	}

	public Long getPurchaseItemId() {
		return purchaseItemId;
	}

	public void setPurchaseItemId(Long purchaseItemId) {
		this.purchaseItemId = purchaseItemId;
	}

	public PurchaseItemLine getPurchaseItemLine() {
		return purchaseItemLine;
	}

	public void setPurchase(PurchaseItemLine purchaseItemLine) {
		this.purchaseItemLine = purchaseItemLine;
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
