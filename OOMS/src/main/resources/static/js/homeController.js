app.controller('homeController',['$scope','$http',function($scope,$http){
	
	$scope.homeDto =[];
	$scope.totalCustomers = 0
	$scope.totalOrders = 0
	$scope.totalSuppliers = 0
	$scope.totalSupplierOrders = 0
	
	$scope.unreadNotifications =0;
	
	
	
	$scope.todaysCustomers = 0
	$scope.todaysOrders = 0
	$scope.todaysSuppliers = 0
	$scope.todaysSupplierOrders = 0
	
	//this should gets executed on page load
	var url = 'http://localhost:8080/user/getHomeData?userId='+localStorage.getItem("id");
	$http({
		url: url,
		method: 'GET'
	}).then(function(resp){
		$scope.homeDto = resp.data;
		
		$scope.totalSuppliers = $scope.homeDto.totalSuppliers;
		$scope.totalOrders = $scope.homeDto.totalOrders;
		$scope.totalCustomers = $scope.homeDto.totalCustomers;
		$scope.totalSupplierOrders = $scope.homeDto.totalSupplierOrders;
		
		$scope.todaysCustomers = $scope.homeDto.todayscustomers.length;
		$scope.todaysOrders = $scope.homeDto.todaysorders.length;
		$scope.todaysSuppliers = $scope.homeDto.todayssuppliers.length;
		$scope.todaysSupplierOrders = $scope.homeDto.todaysSupplierseOrders.length;
		
		$scope.unreadNotifications = $scope.homeDto.unreadNotofications;
		
	},function(){
		$log.log("ERROR");
	})
}])