'use strict';

App.controller('CardController', ['$scope', 'CardService', function($scope, CardService) {
          var self = this;
          self.card={cardId:null, uid:null, user:null};
          self.cards=[];
              
          self.fetchAllCards = function(){
              CardService.fetchAllCards()
                  .then(
      					       function(d) {
      						        self.cards = d;
      					       },
            					function(errResponse){
            						console.error('Error while fetching cards');
            					}
      			       );
          };
           
          self.createCard = function(card){
              CardService.createCard(card)
		              .then(
                          self.fetchAllCards, 
                          function(errResponse){
					               console.error('Error while creating card.');
				              }	
                  );
          };
          
          self.fetchAllCards();

         self.updateCard = function(card, cardId){
              CardService.updateCard(card, cardId)
		              .then(
				              self.fetchAllCards, 
				              function(errResponse){
					               console.error('Error while updating card.');
				              }	
                  );
          };
          
          self.fetchAllCards();

         self.deleteCard = function(cardId){
              CardService.deleteCard(cardId)
		              .then(
				              self.fetchAllCards, 
				              function(errResponse){
					               console.error('Error while deleting card.');
				              }	
                  );
          };

          self.fetchAllCards();

          self.submit = function() {
              if(self.card.cardId==null){
                  console.log('Saving New Card', self.card);    
                  self.createCard(self.card);
              }else{
                  self.updateCard(self.card, self.card.cardId);
                  console.log('card updated with id ', self.card.cardId);
              }
              self.reset();
          };
              
          self.edit = function(cardId){
              console.log('id to be edited', cardId);
              for(var i = 0; i < self.cards.length; i++){
                  if(self.cards[i].cardId == cardId) {
                     self.card = angular.copy(self.cards[i]);
                     break;
                  }
              }
          };
              
          self.remove = function(cardId){
              console.log('id to be deleted', cardId);
              if(self.card.cardId === cardId) {//clean form if the item to be deleted is shown there.
                 self.reset();
              }
              self.deleteCard(cardId);
          };

          
          self.reset = function(){
              self.card={cardId:null, uid:null, user:null};
              $scope.myForm.$setPristine(); //reset Form
          };

      }]);
