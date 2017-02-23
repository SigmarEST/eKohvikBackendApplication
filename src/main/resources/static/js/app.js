'use strict';

var App = angular.module('myApp', [ "ngRoute" ]).config(
		function($routeProvider, $locationProvider) {
			$routeProvider
			.when("/", {
				templateUrl:"home.html",
				controller: "homeController"
			})
			.when("/items", {
				templateUrl : "item.html",
				controller : "ItemController"
			}).when("/users", {
				templateUrl : "user.html",
				controller : "UserController"
			}).when("/stations", {
				templateUrl : "station.html",
				controller : "StationController"
			}).when("/cards", {
				templateUrl : "card.html",
				controller : "CardController"
			}).otherwise({
				redirectTo:'/'
			})

			$locationProvider.html5Mode(true)
		})
