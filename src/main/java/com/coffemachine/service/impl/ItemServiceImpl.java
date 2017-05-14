package com.coffemachine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coffemachine.model.Item;
import com.coffemachine.repository.ItemRepository;
import com.coffemachine.services.ItemService;

@Service("ItemService")
@Transactional
public class ItemServiceImpl implements ItemService{
	
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
		//item.return itemRepository.findAll();
	}
	
	public void deleteItem(Long id){
		itemRepository.delete(id);
		//return itemRepository.findAll();
	}
	
	public void updateItem(Item item){
		itemRepository.save(item);
		//return itemRepository.findAll();
	}
	
	public boolean isItemExist(Item item) {
		return (itemRepository.findByName(item.getName()))!=null;
	}
}
