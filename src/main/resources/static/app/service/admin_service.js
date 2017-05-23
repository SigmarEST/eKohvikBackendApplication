'use strict';

App.factory('AdminService', function($http, $q, URL){

	var backendUrl = URL+'/admin/';

	return {
		
			fetchAllAdmins: function() {
					return $http.get(backendUrl)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching admins');
										return $q.reject(errResponse);
									}
							);
			},
			
			fetchAdmin: function(id){
				return $http.get(backendUrl+id)
				.then(
						function(response){
							return response.data;
						}, 
						function(errResponse){
							console.error('Error while fetching admin');
							return $q.reject(errResponse);
						}
				);
			},
			
		    
		    createAdmin: function(admin){
					return $http.post(backendUrl, admin)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating admin');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateAdmin: function(admin){
					return $http.put(backendUrl, admin)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating admin');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteAdmin: function(id){
					return $http.delete(backendUrl + id)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting admin');
										return $q.reject(errResponse);
									}
							);
			}
		
	};

});