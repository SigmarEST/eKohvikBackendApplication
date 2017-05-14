package com.coffemachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coffemachine.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>{

}
