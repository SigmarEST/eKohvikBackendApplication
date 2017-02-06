package com.coffemachine.card;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
	
	@Autowired
	CardRepository cardRepository;
	
	public List<Card> getAllCards(){
		return (List<Card>) cardRepository.findAll();
	}
	
	public List<Card> getAllCardsByUser(Long id){
		return (List<Card>) cardRepository.findByUser(id);
	}
	
	public Card getCard(Long id){
		return cardRepository.findOne(id);
	}
	
	public void addCard(Card card){
		cardRepository.save(card);
	}
	
	public void updateCard(Card card){
		cardRepository.save(card);
	}

	public void deleteCard(Long id) {
		cardRepository.delete(id);
	}

}
