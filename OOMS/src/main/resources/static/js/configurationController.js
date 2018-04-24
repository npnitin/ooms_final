app.controller('configurationController',['$scope','$http',function($scope,$http){
	
	$scope.conf={};
	var url = 'http://localhost:8080/user/getConfigurationByUserId?userId='+localStorage.getItem("id");
	$http({
		url: url,
		method: 'GET'
	}).then(function(resp){
		$scope.conf = resp.data;
		$scope.receivedOrderEmailSetting = resp.data.receivedOrderEmailSetting;
		$scope.receivedOrderSMSSetting = resp.data.receivedOrderSMSSetting;
		$scope.orderReadyToDispatchEMAIlSetting = resp.data.orderReadyToDispatchEMAIlSetting;
		$scope.orderReadyToDispatchSMSSetting = resp.data.orderReadyToDispatchSMSSetting;
		$scope.threshHoldCount = resp.data.productItemThreshHoldValue;
	},function(){
		$log.log("ERROR");
	})

	$scope.updateConf = function(){
		
		var configuration ={
				receivedOrderEmailSetting :$scope.receivedOrderEmailSetting,
				receivedOrderSMSSetting : $scope.receivedOrderSMSSetting,
				orderReadyToDispatchEMAIlSetting : $scope.orderReadyToDispatchEMAIlSetting,
				orderReadyToDispatchSMSSetting : $scope.orderReadyToDispatchSMSSetting,
				productItemThreshHoldValue : $scope.threshHoldCount
				
		}
		var config = {
				headers:{
					'Content-Type': 'application/json'
				},
			};
		var url = "http://localhost:8080/user/addConfigurationByUserId?userId="+localStorage.getItem("id");
		var data = configuration;
		$http.post(url,data,config).success(function(data,status,headers,config){
			
			if(data.errorCode == "200"){
				$('#popup').modal('show');
			}
			
		}).error(function(data,status,headers,config){
			console.log('ERROR');
		});
		
	}
	
}]);
