package com.coffemachine.card;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {
	
	@Autowired
	CardService cardService;
	
	@RequestMapping("/cards")
	public List<Card> getAllCards(){
		return cardService.getAllCards();
	}
	
	@RequestMapping("/cards/{id}")
	public Card getCardById(@PathVariable Long id){
		return cardService.getCard(id);
	}
	
	@RequestMapping("/cards/{userId}")
	public List<Card> getAllCardsByUser(@PathVariable Long userId){
		return cardService.getAllCardsByUser(userId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value ="/cards/add")
	public void addCard(@RequestBody Card card){
		System.out.println(card.getUid());
		cardService.addCard(card);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/cards/update")
	public void updateCard(@RequestBody Card card){
	   
		cardService.updateCard(card);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/cards/delete/{id}")
	public void deleteCard(@PathVariable Long id){
		cardService.deleteCard(id);
	}

}
