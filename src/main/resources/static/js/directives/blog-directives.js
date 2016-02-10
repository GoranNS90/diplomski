(function() {
	var module = angular.module('blogDirectives', []);
	
	module.directive('footerDirective', function() {
		return {
			restrict: 'E',
			templateUrl: '/js/directives/partials/footer.html'
		}
	});
	
	module.directive('sidebarDirective', function() {
		return {
			restrict: 'E',
			templateUrl: '/js/directives/partials/sideBar.html'
		}
	});
	
	module.directive('headerDirective', function() {
		return {
			restrict: 'E',
			templateUrl: '/js/directives/partials/header.html',
			controller: 'navigation'
		}
	});
	
	module.directive('postdetailsDirective', function() {
		return {
			restrict: 'E',
			templateUrl: '/js/directives/partials/postDetails.html'
		}
	});
	
	module.directive('allpostsDirective', function() {
		return {
			restrict: 'E',
			templateUrl: '/js/directives/partials/allPosts.html'
		}
	});
	
})();