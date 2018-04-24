app.controller('purchaseOrderController',['$scope','$http',function($scope,$http){
	$scope.purchaseOrders =[];
	$scope.currentPurchaseOrder ={};
	
	
	$scope.sortparams=['sortByPurchaseOrderDate Desc','sortByPurchaseOrderDate Asc'];
	$scope.selectedParam;
	
	var url = 'http://localhost:8080/user/getAllPurchaseOrdersByUserId?userId='+localStorage.getItem("id");
	$http({
		url: url,
		method: 'GET'
	}).then(function(resp){
		$scope.purchaseOrders = resp.data;
	},function(){
		$log.log("ERROR");
	})
	
	
	$scope.savePurchaseOrderIdToDelete = function(order){
		$scope.currentPurchaseOrder = order;
	}
	
	$scope.deletePOServer = function(){
		console.log("inside delete");
		var url = 'http://localhost:8080/user/deletePurchaseOrderById?supplierId='+$scope.currentPurchaseOrder.supplier.id+'&poid='+$scope.currentPurchaseOrder.id;
		$http({
			url: url,
			method: 'GET'
		}).then(function(resp){
			alert(resp.data.message);
		},function(){
			$log.log("ERROR");
		})
	}
	
	$scope.savePurchaseOrderIdToview = function(order){
		localStorage.setItem("orderToView",JSON.stringify(order));
		window.location="#/purchaseOrderDetails";
	}
	$scope.SortData = function(){
		var url = 'http://localhost:8080/user/sortData?userId='+localStorage.getItem("id")+'&attr='+$scope.selectedParam+'&entity=purchaseorder';
		$http({
			url: url,
			method: 'GET'
		}).then(function(resp){
			$scope.purchaseOrders = resp.data;
		},function(){
			$log.log("ERROR");
		})
	}
	$scope.changePOstatus = function(order,status){
		var url = 'http://localhost:8080/user/changePurchaseOrderStatus?userId='+localStorage.getItem("id")+'&orderId='+order.id+"&status="+status;
		$http({
			url: url,
			method: 'GET'
		}).then(function(resp){
			alert(resp.data.message);
			$scope.reloadPage();
		},function(){
			$log.log("ERROR");
		})
	}
	
	$scope.reloadPage =function(){
		var url = 'http://localhost:8080/user/getAllPurchaseOrdersByUserId?userId='+localStorage.getItem("id");
		$http({
			url: url,
			method: 'GET'
		}).then(function(resp){
			$scope.purchaseOrders = resp.data;
		},function(){
			$log.log("ERROR");
		})
	}
}])