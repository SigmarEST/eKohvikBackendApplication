package com.coffemachine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coffemachine.model.Card;

public interface CardRepository extends JpaRepository<Card, Long>{
	
	List<Card>findByUserEmail(String email);
	Card findByUid(String uid);

}
