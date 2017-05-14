package com.coffemachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coffemachine.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{
	public Admin findOneByUsername(String username); 
}
