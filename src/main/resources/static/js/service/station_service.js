'use strict';

App.factory('StationService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllStations: function() {
					return $http.get('http://localhost:8080/stations/')
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
					return $http.post('http://localhost:8080/stations/add/', station)
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
		    
		    updateStation: function(station, stationId){
					return $http.put('http://localhost:8080/stations/update/'+stationId, station)
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
		    
			deleteStation: function(stationId){
					return $http.delete('http://localhost:8080/stations/delete/'+stationId)
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

}]);
