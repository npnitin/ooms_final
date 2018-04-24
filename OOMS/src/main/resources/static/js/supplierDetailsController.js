app.controller('supplierDetailsController',['$scope','$http',function($scope,$http){
	$scope.supplier={};
	$scope.totalAmt=0;
	var url = 'http://localhost:8080/user/getSupplierDetailsById?supplierId='+localStorage.getItem("viewsupplierdetailsId");
	$http({
		url: url,
		method: 'GET'
	}).then(function(resp){
		$scope.supplier = resp.data;
		for(var i=0;i < $scope.supplier.orders.length;i++){
			$scope.totalAmt = $scope.totalAmt + $scope.supplier.orders[i].totalCost;
		}
	},function(){
		$log.log("ERROR");
	})
	
	
}])