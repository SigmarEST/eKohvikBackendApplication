package com.coffemachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coffemachine.module.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByemail(String email);

}
