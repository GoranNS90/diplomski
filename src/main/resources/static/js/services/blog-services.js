(function() {
	var module = angular.module('blogServices', []);
	
	module.service('PostService', function($http, $q) {
		var deferred;
		this.addPost = function(post) {
			deferred = $q.defer();
			$http.post('/addPost/', post).then(function(response) {
				deferred.resolve(response);
			},
			function(error) {
				deferred.reject(error);
			});
			return deferred.promise;
		};
		
		this.getPosts = function() {
			deferred = $q.defer();
			$http.get('/getPosts').then(function(response) {
				deferred.resolve(response);
			}, function(error) {
				deferred.reject(error);
			});
			return deferred.promise;
		};
		
		this.removePost = function(id) {
			deferred = $q.defer();
			$http.post('/removePost', id).then(function(response) {
				deferred.resolve(response);
			}, function(error) {
				deferred.reject(error);
			});
			return deferred.promise;
		}
	});
	
	module.service('CommentService', function($http, $q) {
		var deferred;
		
		this.addComment = function(comment) {
			deferred = $q.defer();
			$http.post('/addComment/', comment).then(function(response) {
				deferred.resolve(response);
			}, function(error) {
				deferred.reject(error);
			});
			return deferred.promise;
		};
		
		this.removeComment = function(id) {
			deferred = $q.defer();
			$http.post('/removeComment', id).then(function(response) {
				deferred.resolve(response);
			},function(error){
				deferred.reject(error);
			});
			return deferred.promise;
		}
	})
	
	module.service('UserService', function($http, $q) {
		var deferred;
		
		this.registerUser = function(user) {
			deferred = $q.defer();
			$http.post('/userRegister/', user).then(function(response) {
				deferred.resolve(response);
			}, function(error) {
				deferred.resolve(error);
			});
			return deferred.promise;
		};
		
		this.logout = function() {
			deferred = $q.defer();
			$http.get('/logout', {}).then(function(response) {
				deferred.resolve(response);
			}, function(error) {
				deferred.resolve(error);
			});
			return deferred.promise;
		};
		
		this.authenticate = function(headers) {
			deferred = $q.defer();
			$http.get('/user', {
				headers : headers
			}).then(function(response) {
				deferred.resolve(response);
			}, function(error) {
				deferred.resolve(error);
			});
			return deferred.promise;
		};
	});
	
})();