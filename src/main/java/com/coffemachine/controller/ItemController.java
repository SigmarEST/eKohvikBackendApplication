package com.coffemachine.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coffemachine.model.Item;
import com.coffemachine.services.ItemService;

import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    //for station only
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("items/")
    public List<Item> getAllActiveItems() {
        List<Item> items = itemService.getAllItems();
        List<Item> activeItems = new ArrayList<Item>();
        System.out.println(items.size());
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isAvailable()) {
                activeItems.add(items.get(i));
            }
        }

        return activeItems;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("coffeeMachineItems/")
    public List<Item> getAllActiveCoffeeMachineItems() {
        return itemService.getAllCoffeeMachineItems();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id) {
        System.out.println("Fetching item with id " + id);
        Item item = itemService.getItem(id);
        if (item == null) {
            System.out.println("item with id " + id + " not found");
            return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity<Void> addItem(@RequestBody Item item, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Item " + item.getName());

        if (itemService.isItemExist(item)) {
            System.out.println("An item with name " + item.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        } else {

            itemService.addItem(item);

            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<Item> updateItem(@RequestBody Item item) {
        System.out.println("Updating Item " + item.getItemId());

        Item currentItem = itemService.getItem(item.getItemId());

        if (currentItem == null) {
            System.out.println("Item with id " + item.getItemId() + " not found");
            return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
        }

        currentItem.setName(item.getName());
        currentItem.setPrice(item.getPrice());
        currentItem.setAvailable(item.isAvailable());
        currentItem.setIsFromCoffeeMachine(item.getIsFromCoffeeMachine());

        itemService.updateItem(currentItem);
        return new ResponseEntity<Item>(currentItem, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
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
