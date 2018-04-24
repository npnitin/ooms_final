app.controller('profileController',['$scope','$http',function($scope,$http){
	$scope.profile={};
	
	var url = 'http://localhost:8080/user/getProfileByUserId?userId='+localStorage.getItem("id");
	$http({
		url: url,
		method: 'GET'
	}).then(function(resp){
		$scope.profile = resp.data;
		
	},function(){
		alert("Error while fetching user data from server");
	})
}])