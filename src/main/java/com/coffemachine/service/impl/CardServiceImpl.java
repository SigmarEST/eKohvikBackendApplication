package com.coffemachine.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffemachine.dto.CardDTO;
import com.coffemachine.module.Card;
import com.coffemachine.repository.CardRepository;
import com.coffemachine.services.CardService;

@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
	CardRepository cardRepository;
	
	public List<CardDTO> getAllCards(){
		return (List<CardDTO>) cardRepository.findAll().stream().map(card -> new CardDTO(card.getCardId(), card.getUid(), card.getUser().getEmail())).collect(Collectors.toList());
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

	@Override
	public boolean isCardExist(Card card) {
		return (cardRepository.findByUid(card.getUid()))!=null;
		
	}

	@Override
	public Card getCardByUID(String uid) {
		return cardRepository.findByUid(uid);
	}

}
