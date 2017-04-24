package com.coffemachine.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.coffemachine.dto.CardDTO;
import com.coffemachine.module.Card;
import com.coffemachine.module.User;
import com.coffemachine.services.CardService;
import com.coffemachine.services.UserService;

@RestController
@RequestMapping("/api/card")
public class CardController {

	@Autowired
	CardService cardService;
	@Autowired
	UserService userService;

	@RequestMapping("/")
	public List<CardDTO> getRestAllCards() {
		return cardService.getAllCards();
	}
	
	@RequestMapping("/{uid}")
	public Card getCardByUid(@PathVariable String uid) {
		return cardService.getCardByUID(uid);

	}
	
	@RequestMapping("user/{uid}")
	public User getUserByCardUid(@PathVariable String uid){
		Card card = cardService.getCardByUID(uid);
		if(card != null){
			User user = card.getUser();
			return user;
		}
		return null;
	}
	
	//api/card/email
	@RequestMapping(method = RequestMethod.POST, value ="/email/{email:.+}") 
	public void addCardByEmail(@PathVariable String email, @RequestBody Card card){
		User user = userService.getByEmail(email); 
		if(user != null){
		card.setUser(user);
		card.setCreatedDate(new Date());
		cardService.addCard(card); //cardService.updateCard(card);
		user.getCards().add(card); 
		userService.updateUser(user); 
		}
	}
	
	/*@RequestMapping(method = RequestMethod.POST, value ="/") 
	public void addCard(@RequestBody Card card){
		cardService.addCard(card); //cardService.updateCard(card);
		User user = card.getUser();
		user.getCards().add(card); 
		userService.updateUser(user); 
	}*/

	
	 @RequestMapping(method = RequestMethod.GET, value="/email/{email}") 
	 public List<Card> getAllCardsByUser(@PathVariable String email) { 
		 return cardService.getAllCardsByUserEmail(email); 
	 }

	
	 @RequestMapping(method = RequestMethod.PUT, value = "/")
	 public void updateCard(@RequestBody Card card) {
		 cardService.updateCard(card); 
	 }

	
	 @RequestMapping(method = RequestMethod.DELETE, value = "/{id}") 
	 public void deleteCard(@PathVariable Long id) {
		 Card card = cardService.getCard(id);
		 User user = userService.getUser(card.getUser().getUserId());
		 user.getCards().remove(card);
		 cardService.deleteCard(id); 
	 }
}
