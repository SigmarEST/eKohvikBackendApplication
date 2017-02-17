package com.coffemachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coffemachine.module.Station;

public interface StationRepository extends JpaRepository<Station, Long>{
	Station findByAddress(String address);
}
