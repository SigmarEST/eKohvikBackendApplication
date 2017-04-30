App.controller("HomeController", ['$http','$scope','AuthService', function($http, $scope, AuthService){
	$scope.user = AuthService.user;
                                          }]);