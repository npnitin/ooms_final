app.controller('customerDetailController',['$scope','$http',function($scope,$http){
	
	
	$scope.totalAmt=0;
	$scope.paidAmt=0;
	$scope.balanceAmt=0;
	
	$scope.customer ={};
	var url = 'http://localhost:8080/user/getCustomerByIdAndUserId?userId='+localStorage.getItem("id")+"&customerId="+localStorage.getItem("customerId");
	$http({
		url: url,
		method: 'GET'
	}).then(function(resp){
		$scope.customer = resp.data;
		for(var i=0;i < $scope.customer.orders.length;i++){
			$scope.totalAmt = $scope.totalAmt + $scope.customer.orders[i].totalCost;
			$scope.paidAmt = $scope.paidAmt +  $scope.customer.orders[i].advanceAmount;
			$scope.balanceAmt = $scope.balanceAmt +  $scope.customer.orders[i].remainingbalance;
		}
	},function(){
		console.log("ERROR");
	})
}])