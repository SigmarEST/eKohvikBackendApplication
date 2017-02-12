package com.coffemachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coffemachine.module.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

	Item findByName(String name);

}
