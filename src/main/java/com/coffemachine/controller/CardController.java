package com.coffemachine.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/")
	public List<CardDTO> getRestAllCards() {
		return cardService.getAllCards();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/{uid}")
	public Card getCardByUid(@PathVariable String uid) {
		return cardService.getCardByUID(uid);

	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("user/{uid}")
	public User getUserByCardUid(@PathVariable String uid){
		Card card = cardService.getCardByUID(uid);
		if(card != null){
			User user = card.getUser();
			return user;
		}
		return null;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	
	 @PreAuthorize("hasRole('ROLE_ADMIN')")
	 @RequestMapping(method = RequestMethod.GET, value="/email/{email}") 
	 public List<Card> getAllCardsByUser(@PathVariable String email) { 
		 return cardService.getAllCardsByUserEmail(email); 
	 }

	 @PreAuthorize("hasRole('ROLE_ADMIN')")
	 @RequestMapping(method = RequestMethod.PUT, value = "/")
	 public void updateCard(@RequestBody Card card) {
		 cardService.updateCard(card); 
	 }

	 @PreAuthorize("hasRole('ROLE_ADMIN')")
	 @RequestMapping(method = RequestMethod.DELETE, value = "/{id}") 
	 public void deleteCard(@PathVariable Long id) {
		 Card card = cardService.getCard(id);
		 User user = userService.getUser(card.getUser().getUserId());
		 user.getCards().remove(card);
		 cardService.deleteCard(id); 
	 }
}
