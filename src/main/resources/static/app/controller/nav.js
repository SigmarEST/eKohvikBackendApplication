App
// Creating the Angular Controller
.controller('NavController', ['$http','$scope','AuthService','$state','$rootScope', function($http, $scope, AuthService, $state, $rootScope) {
	$scope.$on('LoginSuccessful', function() {
		$scope.user = AuthService.user;
	});
	$scope.$on('LogoutSuccessful', function() {
		$scope.user = null;
	});
	$scope.logout = function() {
		AuthService.user = null;
		$rootScope.$broadcast('LogoutSuccessful');
		$state.go('login');
	};
}]);
