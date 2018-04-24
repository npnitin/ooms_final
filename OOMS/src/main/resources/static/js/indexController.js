app.controller('indexController',['$scope','$http',function($scope,$http){
	window.location = '#/home';
	$scope.unreadNotifications = localStorage.getItem("unreadNotifications");
}])