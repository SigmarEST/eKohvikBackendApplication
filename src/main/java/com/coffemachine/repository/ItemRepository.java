package com.coffemachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coffemachine.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

	Item findByName(String name);

}
