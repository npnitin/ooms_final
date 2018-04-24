app.controller('contactController',['$scope','$http',function($scope,$http){
	$scope.submitRequest = function(){
		var serviceRequest ={
				
				nameOfUser : $scope.name,
				email : $scope.email,
				subject : $scope.subject,
				message : $scope.message
		}
		var config = {
				headers:{
					'Content-Type': 'application/json'
				},
			};
		var url = "http://localhost:8080/user/addServiceRequest?userId="+localStorage.getItem("id");
		var data = serviceRequest;
		$http.post(url,data,config).success(function(data,status,headers,config){
			console.log(data);
			if(data.errorCode == "200"){
				document.getElementById("frm").reset();
				alert(data.message);
				
			}
			
		}).error(function(data,status,headers,config){
			console.log('ERROR');
		});
		
	} 
}])