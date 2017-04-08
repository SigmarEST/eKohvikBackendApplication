package com.coffemachine.services;

import java.util.List;

import com.coffemachine.dto.CardDTO;
import com.coffemachine.module.Card;

public interface CardService {
	
	public List<CardDTO> getAllCards();
	
	public List<Card> getAllCardsByUserEmail(String email);
	
	public Card getCard(Long id);
	
	public Card getCardByUID(String uid);
	
	public void addCard(Card card);
	
	public void updateCard(Card card);

	public void deleteCard(Long id);
	
	public boolean isCardExist(Card card);

}
