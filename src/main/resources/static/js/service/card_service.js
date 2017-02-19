'use strict';

App.factory('CardService', ['$http', '$q', 'UserService',function($http, $q, UserService){

	return {
		
			fetchAllCards: function() {
					return $http.get('http://localhost:8080/cards/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching cards');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createCard: function(card){
		        UserService.fetchAllUsers()
		    	   .then( function(response){
		    	    	for(var i = 0; i<response.data.length; i++){
		    	    		if(card.user.userId == response.data[i].userId){
		    	    			card.user = response.data[i];
		    	    			
		    	    		}
		    	    	};
		    	    	
			    	    return $http.post('http://localhost:8080/cards/add/', card)
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									console.error('Error while creating card');
									return $q.reject(errResponse);
								}
						);    	   
		    	   
		    	   }
		    	  );
					
		    },
		    
		    updateCard: function(card, cardId){
					return $http.put('http://localhost:8080/cards/update/'+ cardId, card)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating card');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteCard: function(cardId){
					return $http.delete('http://localhost:8080/cards/delete/'+ cardId)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting card');
										return $q.reject(errResponse);
									}
							);
			}
		
	};

}]);
