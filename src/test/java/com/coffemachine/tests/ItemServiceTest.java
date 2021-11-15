package com.coffemachine.tests;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.coffemachine.AbstractTest;
import com.coffemachine.model.Item;
import com.coffemachine.services.ItemService;

import org.junit.Assert;

@Transactional
public class ItemServiceTest extends AbstractTest {
	
	@Autowired
	private ItemService itemService;
	
	
	@Test
	public void testGetAllItems(){
		List<Item> list = itemService.getAllItems();
		
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
		
	}
	
	@Test
	public void testGetItem(){
		Item item = itemService.getItem(2L);
		
		Assert.assertNotNull(item);
		Assert.assertEquals(2, item.getItemId().intValue());
	}
	
	@Test
	public void testAddItem(){
		Item item = new Item();
		item.setItemId(10L);
		item.setAvailable(true);
		item.setName("chocolate");
		item.setPrice(0.40);
		item.setIsFromCoffeeMachine(false);
		itemService.addItem(item);
		
		Assert.assertNotNull(itemService.getItem(10L));
	}
	
	@Test
	public void testDeleteItem(){
		Item item = new Item();
		item.setItemId(5L);
		item.setAvailable(true);
		item.setName("chocolate");
		item.setPrice(0.40);
		item.setIsFromCoffeeMachine(false);
		itemService.addItem(item);
		
		itemService.deleteItem(5L);
		Assert.assertNull(itemService.getItem(5L));
	}

}
