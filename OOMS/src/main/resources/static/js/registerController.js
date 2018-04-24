var app = angular.module('oomsApp',[]);
app.controller('registerController',['$scope','$http',function($scope,$http){
	
	$scope.msg ="";
	$scope.registerUser = function(){
		
		$scope.validateUser();
		
	}
	$scope.validateUser = function(){
		$scope.msg ="";
		if($scope.fname ==="" || $scope.lname === "" || $scope.email === "" || $scope.mobile === "" || $scope.password === "" || $scope.password1 === "" || $scope.fname ===undefined || $scope.lname === undefined || $scope.email === undefined || $scope.mobile === undefined || $scope.password === undefined || $scope.password1 === undefined){
			$scope.msg="All fields are mandatory.!!!";
		}
		else{
			if($scope.password !== $scope.password1){
				$scope.msg="Both passwords do not match..!!";
			}
			else{
				$scope.validationCall();
			}
			
		}
		
		
	}
	$scope.validationCall = function(){
		var url = 'http://localhost:8080/user/validateEmailAndPhone?email='+$scope.email+"&phone="+$scope.mobile;
		$http({
			url: url,
			method: 'GET'
		}).then(function(resp){
			if(resp.data.errorCode == "200"){
				$scope.makeCall();
			}
			else{
				$scope.msg = resp.data.message;
			}
		},function(){
			console.log("ERROR");
		})
	}
	$scope.makeCall = function(){
		
		var user={
				name : $scope.fname+" "+$scope.lname,
				email : $scope.email,
				phone : $scope.mobile,
				password : $scope.password
		}
		
		var config = {
				headers:{
					'Content-Type': 'application/json'
				},
			};
		var url = "http://localhost:8080/user/createUser";
		var data = user;
		$http.post(url,data,config).success(function(data,status,headers,config){
			
			if(data.errorCode == "200"){
				$scope.msg="Registration successful,please check your mailbox to activate your account";
			}
			
		}).error(function(data,status,headers,config){
			console.log('ERROR');
		});
		
	
	}
}])