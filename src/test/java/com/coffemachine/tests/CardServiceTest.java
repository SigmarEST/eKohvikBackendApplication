package com.coffemachine.tests;

import org.junit.Assert;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.coffemachine.AbstractTest;
import com.coffemachine.dto.CardDTO;
import com.coffemachine.model.Card;
import com.coffemachine.services.CardService;
import com.coffemachine.services.UserService;

@Transactional
public class CardServiceTest extends AbstractTest {
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testGetAllCards(){
		List<CardDTO> list = cardService.getAllCards();
		Assert.assertNotNull(list);
		Assert.assertEquals(4, list.size());
		
	}
	
	@Test
	public void testGetAllCardsByUserEmail(){
		List<Card> cards = cardService.getAllCardsByUserEmail("john@gmail.com");
		Assert.assertNotNull(cards);
		Assert.assertEquals(1, cards.size());
	}
	
	@Test
	public void testGetCard(){
		Card card = cardService.getCard(5L);
		Assert.assertNotNull(card);
		Assert.assertEquals(5, card.getCardId().intValue());
	}
	
	@Test
	public void testGetCardByUID(){
		Card card = cardService.getCardByUID("2b753a77e1740fe24365d9ed50582fb403af4c6255813c68f62a25631878f2cecafea0b04355f3a1c5261d54098f1cb592b6cfa096c10600948b0d4a51519380");
		Assert.assertNotNull(card);
		Assert.assertEquals("2b753a77e1740fe24365d9ed50582fb403af4c6255813c68f62a25631878f2cecafea0b04355f3a1c5261d54098f1cb592b6cfa096c10600948b0d4a51519380", card.getUid());
	}
	
	@Test
	public void testAddCard(){
		Card card = new Card();
		card.setCreatedDate(new Date());
		card.setName("testCard");
		card.setUser(userService.getByEmail("john@gmail.com"));
		card.setUid("222222");
		cardService.addCard(card);
		
		Assert.assertNotNull(cardService.getCardByUID("222222"));
	}
	
	@Test
	public void testDeleteCard(){
		cardService.deleteCard(cardService.getCardByUID("2b753a77e1740fe24365d9ed50582fb403af4c6255813c68f62a25631878f2cecafea0b04355f3a1c5261d54098f1cb592b6cfa096c10600948b0d4a51519380").getCardId());
		Assert.assertNull(cardService.getCardByUID("2b753a77e1740fe24365d9ed50582fb403af4c6255813c68f62a25631878f2cecafea0b04355f3a1c5261d54098f1cb592b6cfa096c10600948b0d4a51519380"));
	}
	
}
