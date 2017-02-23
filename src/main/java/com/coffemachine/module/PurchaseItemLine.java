package com.coffemachine.module;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="purchaseItemLine")
public class PurchaseItemLine implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6427404512470822698L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long purchaseItemId;
	
	@ManyToOne
    @JoinColumn(name="purchaseId")
	private Purchase purchase;
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "purchaseItemLine", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PurchaseItem> purchaseItems = new HashSet<PurchaseItem>();
    
	public PurchaseItemLine(){
		super();
	}
	
	public PurchaseItemLine(Long purchaseItemId, Purchase purchase, Set<PurchaseItem> purchaseItems) {
		super();
		this.purchaseItemId = purchaseItemId;
		this.purchase = purchase;
		this.purchaseItems = purchaseItems;
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

	public Set<PurchaseItem> getPurchaseItems() {
		return purchaseItems;
	}

	public void setPurchaseItems(Set<PurchaseItem> purchaseItems) {
		this.purchaseItems = purchaseItems;
	}
	
    
}
