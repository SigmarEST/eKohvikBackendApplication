package com.coffemachine.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coffemachine.model.Purchase;
import com.coffemachine.model.User;
import com.coffemachine.services.PurchaseService;
import com.coffemachine.services.UserService;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {
	
	@Autowired
	PurchaseService purchaseService;
	
	@Autowired
	UserService userService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/")
	public List<Purchase> getAllPurchases(){
		return purchaseService.getAllPurchases();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/{id}")
	public Long getPurchase(@PathVariable Long id){
		return purchaseService.getPurchase(id).getUser().getUserId();
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping("/purchases/{id}")
	public List<Purchase> getPurchaseByUser(@PathVariable Long id){
		User user = userService.getUser(id);
		if(user != null){
			return purchaseService.getByUser(user);
		}
		return null;

	}
	
	//for only station
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(method = RequestMethod.POST, value = "/")
	public void addPurchase(@RequestBody Purchase purchase){
		purchase.setDateTime(new Date());
		double cost = 0;
		for(int i=0; i<purchase.getItems().size(); i++){
			cost += purchase.getItems().get(i).getPrice();
		}
		purchase.setCost(new BigDecimal(cost).setScale(2, RoundingMode.HALF_UP).doubleValue());
		User user = purchase.getUser();
		user.setBalance(new BigDecimal(user.getBalance()-cost).setScale(2, RoundingMode.HALF_UP).doubleValue());
		userService.updateUser(user);
		purchaseService.addPurchase(purchase);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deletePurchase(@PathVariable Long id){		
		User user = userService.getUser(purchaseService.getPurchase(id).getUser().getUserId());
		user.setBalance(new BigDecimal(user.getBalance()+purchaseService.getPurchase(id).getCost()).setScale(2, RoundingMode.HALF_UP).doubleValue());
		userService.updateUser(user);
		purchaseService.deletePurchase(id);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PUT, value = "/")
	public void updatePurchase(@RequestBody Purchase purchase){
		purchaseService.updatePurchase(purchase);
	}

}
