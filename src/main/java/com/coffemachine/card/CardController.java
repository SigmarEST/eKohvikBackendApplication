package com.coffemachine.card;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coffemachine.user.User;
import com.coffemachine.user.UserService;

@RestController
public class CardController {
	
	@Autowired
	CardService cardService;
	@Autowired
	UserService userService;
	
	@RequestMapping("/cards")
	public List<Card> getAllCards(){
		return cardService.getAllCards();
	}
	
	@RequestMapping("/cards/{id}")
	public Card getCardById(@PathVariable Long id){
		return cardService.getCard(id);
	}
	
	@RequestMapping("/cards/{userId}")
	public List<Card> getAllCardsByUser(@PathVariable String email){
		return cardService.getAllCardsByUserEmail(email);
	}
	
	@RequestMapping(method = RequestMethod.POST, value ="/cards/add/{email:.+}")
	public void addCard(@PathVariable String email, @RequestBody Card card){
		User user  = userService.getByEmail(email);
		card.setUser(user);
		cardService.addCard(card);
		//cardService.updateCard(card);
		user.getCards().add(card);
		userService.updateUser(user);
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
