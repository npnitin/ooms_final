app.controller('notificationController',['$scope','$http',function($scope,$http){
	$scope.notifications={};
	$scope.color='#3B5998';
	var url = 'http://localhost:8080/user/getNotificationsByUserId?userId='+localStorage.getItem("id");
	$http({
		url: url,
		method: 'GET'
	}).then(function(resp){
		$scope.notifications = resp.data;
	},function(){
		console.log("ERROR");
	})

}])