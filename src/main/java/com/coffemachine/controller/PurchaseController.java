package com.coffemachine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coffemachine.module.Purchase;
import com.coffemachine.services.PurchaseService;

@RestController
//@RequestMapping("/coffemachine")
public class PurchaseController {
	
	@Autowired
	PurchaseService purchaseService;
	
	@RequestMapping("/purchases")
	public List<Purchase> getAllPurchases(){
		return purchaseService.getAllPurchases();
	}
	
	@RequestMapping("/purchases/{id}")
	public Long getPurchase(@PathVariable Long id){
		return purchaseService.getPurchase(id).getUser().getUserId();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/purchases/add")
	public void addPurchase(@RequestBody Purchase purchase){
		purchaseService.addPurchase(purchase);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/purchases/delete/{id}")
	public void deletePurchase(@PathVariable Long id){
		purchaseService.deletePurchase(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/purchases/update")
	public void updatePurchase(@RequestBody Purchase purchase){
		purchaseService.updatePurchase(purchase);
	}

}
