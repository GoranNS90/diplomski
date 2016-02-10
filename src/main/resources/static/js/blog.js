(function(){
	angular.module('blog', [ 'ngRoute', 'blogControllers', 'blogDirectives' ]).config(function($routeProvider, $httpProvider) {
	
		$routeProvider.when('/', {
			templateUrl : 'home.html',
			controller : 'home'
		}).when('/login', {
			templateUrl : 'login.html',
			controller : 'navigation'
		}).when('/register', {
			templateUrl : 'register.html',
			controller : 'register'
		}).when('/addPost', {
			templateUrl : 'addPost.html',
			controller: 'post'
		}).otherwise('/');
	
		$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
	
	});
})();
