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
import org.springframework.web.util.UriComponentsBuilder;

import com.coffemachine.dto.CardBO;
import com.coffemachine.module.Card;
import com.coffemachine.module.User;
import com.coffemachine.services.CardService;
import com.coffemachine.services.UserService;

@RestController
// @RequestMapping("/coffemachine")
public class CardController {

	@Autowired
	CardService cardService;
	@Autowired
	UserService userService;

	/*
	 * @RequestMapping("/cards") public List<Card> getAllCards(){ return
	 * cardService.getAllCards(); }
	 */

	@RequestMapping("/cards")
	public ResponseEntity<List<CardBO>> getAllCards() {
		List<CardBO> cards = cardService.getAllCards();
		System.out.println(cards);
		
		if (cards.isEmpty()) {
			return new ResponseEntity<List<CardBO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CardBO>>(cards, HttpStatus.OK);
	}

	/*
	 * @RequestMapping("/cards/{id}") public Card getCardById(@PathVariable Long
	 * id){ return cardService.getCard(id); }
	 */

	@RequestMapping("/cards/{id}")
	public ResponseEntity<Card> getCard(@PathVariable Long id) {
		System.out.println("Fetching card with id " + id);
		Card card = cardService.getCard(id);
		if (card == null) {
			System.out.println("card with id " + id + " not found");
			return new ResponseEntity<Card>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Card>(card, HttpStatus.OK);
	}

	/*@RequestMapping("/cards/{userId}")
	public List<Card> getAllCardsByUser(@PathVariable String email) {
		return cardService.getAllCardsByUserEmail(email);
	}
*/
	/*
	 * @RequestMapping(method = RequestMethod.POST, value
	 * ="/cards/add/{email:.+}") public void addCard(@PathVariable String
	 * email, @RequestBody Card card){ User user =
	 * userService.getByEmail(email); card.setUser(user);
	 * cardService.addCard(card); //cardService.updateCard(card);
	 * user.getCards().add(card); userService.updateUser(user); }
	 */

	@RequestMapping(method = RequestMethod.POST, value = "/cards/add")
	public ResponseEntity<Void> addCard(@RequestBody Card card, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Card " + card.getUid());

		if (cardService.isCardExist(card)) {
			System.out.println("card with uid " + card.getUid() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			System.out.println(card.getUser().getEmail());
			//User user = userService.getUser(card.getUser().getUserId());
			//card.setUser(user);
			User user = card.getUser();
			card.setUserEmail(user.getEmail());
			cardService.addCard(card);

			user.getCards().add(card);
			userService.updateUser(user);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/cards/{id}").buildAndExpand(card.getCardId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
	}

	/*@RequestMapping(method = RequestMethod.PUT, value = "/cards/update")
	public void updateCard(@RequestBody Card card) {

		cardService.updateCard(card);
	}*/
	
	 @RequestMapping(value = "/cards/update/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Card> updateCard(@PathVariable("id") Long id, @RequestBody Card card) {
	        System.out.println("Updating Card " + id);
	         
	        Card currentCard = cardService.getCard(id);
	         
	        if (currentCard==null) {
	            System.out.println("card with id " + id + " not found");
	            return new ResponseEntity<Card>(HttpStatus.NOT_FOUND);
	        }
	        
	        User oldUser = currentCard.getUser();
	        oldUser.getCards().remove(currentCard);
	 
	        currentCard.setUid(card.getUid());
	        currentCard.setUser(card.getUser());
	        
	        User user = userService.getByEmail(card.getUser().getEmail());
	        user.getCards().add(currentCard);
	         
	        cardService.updateCard(currentCard);
	        return new ResponseEntity<Card>(currentCard, HttpStatus.OK);
	    }
	 

	/*@RequestMapping(method = RequestMethod.DELETE, value = "/cards/delete/{id}")
	public void deleteCard(@PathVariable Long id) {
		cardService.deleteCard(id);
	}*/
	 
	 @RequestMapping(value = "/cards/delete/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Card> deleteCard(@PathVariable("id") Long id) {
	        System.out.println("Fetching & Deleting Card with id " + id);
	 
	        Card card = cardService.getCard(id);
	        if (card == null) {
	            System.out.println("Unable to delete. Card with id " + id + " not found");
	            return new ResponseEntity<Card>(HttpStatus.NOT_FOUND);
	        }
	        
	        User user = card.getUser();
	        user.getCards().remove(card);
	 
	        cardService.deleteCard(id);
	        return new ResponseEntity<Card>(HttpStatus.NO_CONTENT);
	    }

}
