angular.module('myApp').config(function($stateProvider, $urlRouterProvider) {
			
		// the ui router will redirect if a invalid state has come.
		$urlRouterProvider.otherwise('/page-not-found');
		// parent view - navigation state
		$stateProvider.state('nav', {
			abstract : true,
			url : '',
			views : {
				'nav@' : {
					templateUrl : 'app/views/nav.html',
					controller : 'NavController'
				}
			}
		}).state('login', {
			parent : 'nav',
			url : '/login',
			views : {
				'content@' : {
					templateUrl : 'app/views/login.html',
					controller : 'LoginController'
				}
			}
		}).state('users', {
			parent : 'nav',
			url : '/users',
			data : {
				role : 'ADMIN'
			},
			views : {
				'content@' : {
					templateUrl : 'app/views/user.html',
					controller : 'UserController',
				}
			}
		}).state('items', {
			parent : 'nav',
			url : '/items',
			data : {
				role : 'ADMIN'
			},
			views : {
				'content@' : {
					templateUrl : 'app/views/item.html',
					controller : 'ItemController',
				}
			}
		}).state('cards', {
			parent : 'nav',
			url : '/cards',
			data : {
				role : 'ADMIN'
			},
			views : {
				'content@' : {
					templateUrl : 'app/views/card.html',
					controller : 'CardController',
				}
			}
		}).state('stations', {
			parent : 'nav',
			url : '/stations',
			data : {
				role : 'ADMIN'
			},
			views : {
				'content@' : {
					templateUrl : 'app/views/station.html',
					controller : 'StationController',
				}
			}
		}).state('purchases', {
			parent : 'nav',
			url : '/purchases',
			data : {
				role : 'ADMIN'
			},
			views : {
				'content@' : {
					templateUrl : 'app/views/purchase.html',
					controller : 'PurchaseController',
				}
			}
		}).state('home', {
			parent : 'nav',
			url : '/',
			views : {
				'content@' : {
					templateUrl : 'app/views/home.html',
					controller : 'HomeController'
				}
			}
		}).state('page-not-found', {
			parent : 'nav',
			url : '/page-not-found',
			views : {
				'content@' : {
					templateUrl : 'app/views/page-not-found.html',
					controller : 'PageNotFoundController'
				}
			}
		}).state('access-denied', {
			parent : 'nav',
			url : '/access-denied',
			views : {
				'content@' : {
					templateUrl : 'app/views/access-denied.html',
					controller : 'AccessDeniedController'
				}
			}
		})
		
		//$locationProvider.html5Mode(true)
		
});
