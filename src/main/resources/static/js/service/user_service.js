'use strict';

App.factory('UserService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllUsers: function() {
					return $http.get('http://localhost:8080/users/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching users');
										return $q.reject(errResponse);
									}
							);
			},
			
			fetchUser: function(userId){
				return $http.get('http://localhost:8080/users/'+userId)
				.then(
						function(response){
							return response.data;
						}, 
						function(errResponse){
							console.error('Error while fetching users');
							return $q.reject(errResponse);
						}
				);
			},
			
			fetchUserByEmail: function(email){
				return $http.get('http://localhost:8080/users/email/'+email)
				.then(
						function(response){
							return response.data;
						}, 
						function(errResponse){
							console.error('Error while fetching user with '+ email);
							return $q.reject(errResponse);
						}
				);
			},
		    
		    createUser: function(user){
					return $http.post('http://localhost:8080/users/add/', user)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating user');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateUser: function(user, userId){
					return $http.put('http://localhost:8080/users/update/'+ userId, user)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating user');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteUser: function(userId){
					return $http.delete('http://localhost:8080/users/delete/'+ userId)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting user');
										return $q.reject(errResponse);
									}
							);
			}
		
	};

}]);
