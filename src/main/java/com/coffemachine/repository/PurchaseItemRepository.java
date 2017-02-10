package com.coffemachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coffemachine.module.PurchaseItem;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {

}
