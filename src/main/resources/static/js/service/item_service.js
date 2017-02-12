'use strict';

App.factory('ItemService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllItems: function() {
					return $http.get('http://localhost:8080/items/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching items');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createItem: function(item){
					return $http.post('http://localhost:8080/items/add/', item)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating item');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateItem: function(item, itemId){
					return $http.put('http://localhost:8080/items/update/'+itemId, item)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating item');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteItem: function(itemId){
					return $http.delete('http://localhost:8080/items/delete/'+itemId)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting item');
										return $q.reject(errResponse);
									}
							);
			}
		
	};

}]);
