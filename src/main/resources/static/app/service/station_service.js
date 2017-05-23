'use strict';

App.factory('StationService', function($http, $q, URL){

	var backendUrl = URL+'/station/';

	return {
		
			fetchAllStations: function() {
					return $http.get(backendUrl)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching stations');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createStation: function(station){
					return $http.post(backendUrl, station)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating station');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateStation: function(station){
					return $http.put(backendUrl, station)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating station');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteStation: function(id){
					return $http.delete(backendUrl+id)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting station');
										return $q.reject(errResponse);
									}
							);
			}
		
	};

});
