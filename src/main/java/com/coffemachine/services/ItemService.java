package com.coffemachine.services;

import java.util.List;

import com.coffemachine.model.Item;

public interface ItemService {
	
	public List<Item> getAllItems();
	
	public Item getItem(Long id);
	
	public void addItem(Item item);
	
	public void deleteItem(Long id);
	
	public void updateItem(Item item);
	
	public boolean isItemExist(Item item);
}
