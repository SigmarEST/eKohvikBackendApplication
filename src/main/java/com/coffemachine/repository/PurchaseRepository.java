package com.coffemachine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coffemachine.model.Purchase;
import com.coffemachine.model.User;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>{
	
	public List<Purchase>findByUser(User user);

}
