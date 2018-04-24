app.controller('loginController',['$scope','$http',function($scope,$http){
	
	$scope.msg="";
	$scope.doLogin = function(){
		
	var credential={
			
			email : $scope.userName,
			password : $scope.password
	}
	var config = {
			headers:{
				'Content-Type': 'application/json'
			},
		};
	var data = credential;
	var url = 'http://localhost:8080/user/doLogin';
	$http.post(url,data,config).success(function(data,status,headers,config){
		
		if(data.errorCode == "200"){
			localStorage.setItem("email", credential.email);
			localStorage.setItem("token", data.message);
			localStorage.setItem("id", data.id);
			window.location ='index.html'
		}
		else{
			$scope.msg =data.message;
		}
		
	}).error(function(data,status,headers,config){
		console.log('ERROR');
	});
	
	}
	
	$scope.doLogout = function(){
		localStorage.clear();
		/*localStorage.removeItem("email");
		localStorage.removeItem("token");*/
		window.location='login.html';
	}
}])