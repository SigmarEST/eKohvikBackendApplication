package com.coffemachine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffemachine.module.Card;
import com.coffemachine.repository.CardRepository;

@Service
public class CardService {
	
	@Autowired
	CardRepository cardRepository;
	
	public List<Card> getAllCards(){
		return (List<Card>) cardRepository.findAll();
	}
	
	public List<Card> getAllCardsByUserEmail(String email){
		return (List<Card>) cardRepository.findByUserEmail(email);
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
