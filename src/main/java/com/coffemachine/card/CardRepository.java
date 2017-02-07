package com.coffemachine.card;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long>{
	
	public List<Card>findByUserEmail(String email);

}
