package com.coffemachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coffemachine.model.Station;

public interface StationRepository extends JpaRepository<Station, Long>{
	public Station findByAddress(String address);
	public Station findOneByUsername(String username);
}
