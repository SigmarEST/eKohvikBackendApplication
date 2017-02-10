package com.coffemachine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffemachine.module.Purchase;
import com.coffemachine.repository.PurchaseRepository;

@Service
public class PurchaseService {
	
	@Autowired
	PurchaseRepository purchaseRepository;
	
	public List<Purchase> getAllPurchases(){
		return (List<Purchase>) purchaseRepository.findAll();
	}
	
	public Purchase getPurchase(Long id){
		return purchaseRepository.findOne(id);
	}
	
	public void addPurchase(Purchase purchase){
		purchaseRepository.save(purchase);
	}
	
	public void deletePurchase(Long id){
		purchaseRepository.delete(id);
	}
	
	public void updatePurchase(Purchase purchase){
		purchaseRepository.save(purchase);
	}

}
