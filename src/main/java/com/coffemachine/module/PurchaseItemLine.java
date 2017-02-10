package com.coffemachine.module;

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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="purchaseItemLine")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="idPurchaseItem")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PurchaseItemLine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long purchaseItemId;
	
	@ManyToOne
    @JoinColumn(name="purchaseId")
	private Purchase purchase;
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "purchaseItemLine", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PurchaseItem> purchaseItems = new HashSet<PurchaseItem>();
	

}
