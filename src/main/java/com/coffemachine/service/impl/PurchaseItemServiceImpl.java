package com.coffemachine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffemachine.module.PurchaseItem;
import com.coffemachine.repository.PurchaseItemRepository;
import com.coffemachine.services.PurchaseItemService;


@Service
public class PurchaseItemServiceImpl implements PurchaseItemService {
	
	@Autowired
	PurchaseItemRepository purchaseItemRepository;
	
	public List<PurchaseItem> findAllPurchaseItem(){
		return (List<PurchaseItem>) purchaseItemRepository.findAll();
	}
	
	public PurchaseItem findPurchaseItem(Long id){
		return purchaseItemRepository.findOne(id);
	}
	
	public void addPurchaseItem(PurchaseItem purchaseItem){
		purchaseItemRepository.save(purchaseItem);
	}
	
	public void deletePuchaseItem(Long id){
		purchaseItemRepository.delete(id);
	}
	
	public void updatePurchaseItem(PurchaseItem purchaseItem){
		purchaseItemRepository.save(purchaseItem);
	}
	
	

}
