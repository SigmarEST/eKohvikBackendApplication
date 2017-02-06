package com.coffemachine.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
	
	@Autowired
	ItemRepository itemRepository;
	
	public List<Item> getAllItems(){
		return  (List<Item>) itemRepository.findAll();
	}
	
	public Item getItem(Long id){
		return itemRepository.findOne(id);
	}
	
	public void addItem(Item item){
		itemRepository.save(item);
	}
	
	public void deleteItem(Long id){
		itemRepository.delete(id);
	}
	
	public void updateItem(Item item){
		itemRepository.save(item);
	}
}
