'use strict';

var App = angular.module('myApp',["ngRoute"])
		 .config(function($routeProvider, $locationProvider){
		  $routeProvider
		    .when("/items", {
			  templateUrl:"item.html",
			  controller: "ItemController" 
		     })
		    .when("/users", {
			  templateUrl:"user.html",
			  controller: "UserController" 
		    })
		    .when("/stations", {
			  templateUrl:"station.html",
			  controller: "StationController" 
		    })
		    .when("/cards", {
			  templateUrl:"card.html",
			  controller: "CardController" 
		  })
		  
		  $locationProvider.html5Mode(true)
		})


