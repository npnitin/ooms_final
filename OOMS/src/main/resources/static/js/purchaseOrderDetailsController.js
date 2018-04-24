app.controller('purchaseOrderDetailsController',['$scope','$http',function($scope,$http){
	
	$scope.order = JSON.parse(localStorage.getItem("orderToView"));
	console.log($scope.order);
	var url = 'http://localhost:8080/user/getPurchaseOrderdetailsById?supplierId='+$scope.order.supplier.id+'&poId='+$scope.order.id;
	$http({
		url: url,
		method: 'GET'
	}).then(function(resp){
		console.log( resp.data);
	},function(){
		$log.log("ERROR");
	})
}])