(function(){
	var module = angular.module('blogControllers', [ 'blogServices' ]);
	
	module.controller('navigation', function($rootScope, $scope, $http, $location, $route, UserService) {
		
		$rootScope.activeTab = 'login';
		$rootScope.collapse = false;
		
		$scope.collapseFunction = function() {
			console.log($rootScope.collapse);
			if ($rootScope.collapse) {
				$rootScope.collapse = false;
			} else {
				$rootScope.collapse = true;
			}
		}
		
		$scope.tab = function(route) {
			return $route.current && route === $route.current.controller;
		};

		var authenticate = function(credentials, callback) {

			var headers = credentials ? {
				authorization : "Basic " + btoa(credentials.username + ":" + credentials.password)
			} : {};
			
			UserService.authenticate(headers).then(function(response) {
				if (response.data.name) {
					$rootScope.role = response.data.roles[0];
					console.log($scope.role);
					$rootScope.authenticated = true;
				} else {
					$rootScope.authenticated = false;
				}
				callback && callback($rootScope.authenticated);
			}, function(error) {
				$rootScope.authenticated = false;
				callback && callback(false);
			});
		};

		authenticate();

		$scope.credentials = {};
		$scope.login = function() {
			
			authenticate($scope.credentials, function(authenticated) {
				if (authenticated) {
					$location.path("/");
					$scope.error = false;
					$rootScope.authenticated = true;
				} else {
					$location.path("/login");
					$scope.error = true;
					$rootScope.authenticated = false;
				}
			});
		};

		$scope.logout = function() {
			UserService.logout().then(function(response) {
				$rootScope.authenticated = false;
				$rootScope.role = '';
				$location.path("/");
			}, function(error) {
				console.log(error);
				$rootScope.authenticated = true;
			});
		};

	});
	
	module.controller('home', function($scope, $http, $rootScope, PostService, CommentService) {
		$rootScope.activeTab = 'home';
		$scope.showPosts = true;
		$scope.posts = {};
		$scope.comment = {};
		$scope.selectedPost = {};
		
		
		$scope.showSelectedPostDetails = function(post) {
			$scope.selectedPost = post;
			$scope.showPosts = false;
		};
		
		$scope.getAllPosts = function() {
			$scope.showPosts = true;
			PostService.getPosts().then(function(response) {
				$scope.posts = response.data;
			}, function(error) {
				console.log(error);
			});
		};
		
		$scope.getAllPosts();
		
		$scope.addComment = function() {
			$scope.comment.postId = $scope.selectedPost.id;
			CommentService.addComment($scope.comment).then(function(response) {
				$scope.selectedPost = response.data;
				$scope.comment.content = "";
			}, function(error) {
				console.log(error);
			});
		};
		
		$scope.removePost = function(id) {
			
			PostService.removePost(id).then(function(response) {
				$scope.getAllPosts();
			}, function(error) {
				console.log(error);
			});
		};
		
		$scope.removeComment = function(id) {
			
			CommentService.removeComment(id).then(function(response) {
				console.log(response);
				$scope.selectedPost = {};
				$scope.selectedPost = response.data;
			}, function(error){
				console.log(error);
			});
		};
	});
	
	module.controller('register', function($scope, $http, $location, $rootScope, UserService, $timeout) {
		$scope.notEqual = false;
		$rootScope.activeTab = 'register';
		$scope.notification = false;
		$scope.register = function() {
			if ($scope.user.password != $scope.user.retypePassword) {
				$scope.notEqual = true;
			} else {
				UserService.registerUser($scope.user).then(function(response) {
					$scope.notEqual = false;
					$scope.notification = true;
					$timeout( function() { 
						$scope.notification = false;
						$location.path("/");
					}, 2000);
				}, function(error) {
					$scope.notEqual = false;
				});
			}
		}
	});
	
	module.controller('post', function($scope, $http, $rootScope, PostService, $location) {
		$rootScope.activeTab = 'addPost';
		
		$scope.addPost = function() {
			PostService.addPost($scope.post).then(function(response) {
				$location.path("/");
			}, function(error) {
				console.log(error)
			});
		};	
	});
})();