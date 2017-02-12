package com.coffemachine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coffemachine.module.Item;
import com.coffemachine.services.ItemService;

import org.springframework.web.util.UriComponentsBuilder;

@RestController
//@RequestMapping("/coffemachine")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@RequestMapping("/items")
	public ResponseEntity<List<Item>> getAllItems(){
		List<Item> items= itemService.getAllItems();
		if(items.isEmpty()){
            return new ResponseEntity<List<Item>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}
	
	@RequestMapping("/items/{id}")
	public ResponseEntity<Item> getItem(@PathVariable Long id){
		 System.out.println("Fetching item with id " + id);
	        Item item = itemService.getItem(id);
	        if (item == null) {
	            System.out.println("item with id " + id + " not found");
	            return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<Item>(item, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/items/add")
	public ResponseEntity<Void> addItem(@RequestBody Item item,   UriComponentsBuilder ucBuilder){
		 System.out.println("Creating Item " + item.getName());
		 
	        if (itemService.isItemExist(item)) {
	            System.out.println("An item with name " + item.getName() + " already exist");
	            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	        }
	        else{
	 
	        itemService.addItem(item);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/items/{id}").buildAndExpand(item.getItemId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	        }
	}
	
	 @RequestMapping(value = "/items/update/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Item> updateItem(@PathVariable("id") Long id, @RequestBody Item item) {
	        System.out.println("Updating Item " + id);
	         
	        Item currentItem = itemService.getItem(id);
	         
	        if (currentItem==null) {
	            System.out.println("Item with id " + id + " not found");
	            return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
	        }
	 
	        currentItem.setName(item.getName());
	        currentItem.setPrice(item.getPrice());
	        currentItem.setAvailable(item.isAvailable());
	         
	        itemService.updateItem(currentItem);
	        return new ResponseEntity<Item>(currentItem, HttpStatus.OK);
	    }
	 
	  @RequestMapping(value = "/items/delete/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Item> deleteItem(@PathVariable("id") Long id) {
	        System.out.println("Fetching & Deleting Item with id " + id);
	 
	        Item item = itemService.getItem(id);
	        if (item == null) {
	            System.out.println("Unable to delete. Item with id " + id + " not found");
	            return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
	        }
	 
	        itemService.deleteItem(id);
	        return new ResponseEntity<Item>(HttpStatus.NO_CONTENT);
	    }

}
