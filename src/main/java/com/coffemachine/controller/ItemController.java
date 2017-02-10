package com.coffemachine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coffemachine.module.Item;
import com.coffemachine.service.ItemService;

@RestController
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@RequestMapping("/items")
	public List<Item> getAllItems(){
		return itemService.getAllItems();
	}
	
	@RequestMapping("/items/{id}")
	public Item getItem(@PathVariable Long id){
		return itemService.getItem(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/items/add")
	public void addItem(@RequestBody Item item){
		itemService.addItem(item);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/items/update")
	public void updateItem(@RequestBody Item item){
		itemService.updateItem(item);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/items/delete/{id}")
	public void deleteItem(@PathVariable Long id){
		itemService.deleteItem(id);
	}

}
