app.controller("orderdetailsController",['$scope','$http',function($scope,$http){
	$scope.order={};
	//this should gets executed on page load
	var url = 'http://localhost:8080/user/getOrderDetailsByOrderId?orderId='+localStorage.getItem("orderId");
	$http({
		url: url,
		method: 'GET'
	}).then(function(resp){
		$scope.order = resp.data;
	},function(){
		$log.log("ERROR");
	})
}])	