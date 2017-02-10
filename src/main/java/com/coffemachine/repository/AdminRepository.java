package com.coffemachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coffemachine.module.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{

}
