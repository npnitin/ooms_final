app.controller('orderController',['$scope','$http','$route',function($scope,$http,$route){
	$scope.orders=[];
	$scope.orderIdToDelete=0;
	
	$scope.sortparams=['sortByOrderDate Desc','sortByOrderDate Asc'];
	$scope.selectedParam;
	//this should gets executed on page load
		var url = 'http://localhost:8080/user/getOrdersByUserId?userId='+localStorage.getItem("id");
		$http({
			url: url,
			method: 'GET'
		}).then(function(resp){
			$scope.orders = resp.data;
		},function(){
			console.log("ERROR");
		})
	
		$scope.addOrder = function(){
			
			var address ={
					street :$scope.cstreet,
					city : $scope.city,
					state : $scope.cstate,
					pincode : $scope.cpincode
					
			}
			var customer={
					name : $scope.cname,
					email : $scope.cemail,
					mobile : $scope.cphone,
					address : address
			}
			var config = {
					headers:{
						'Content-Type': 'application/json'
					},
				};
			var url = "http://localhost:8080/user/addCustomer?userId="+localStorage.getItem("id");
			var data = customer;
			$http.post(url,data,config).success(function(data,status,headers,config){
				
				if(data.errorCode == "200"){
					
				}
				
			}).error(function(data,status,headers,config){
				console.log('ERROR');
			});
			
		}
		
		$scope.saveOrderIdAndRedirectToOrderDetails = function(order){
			localStorage.setItem("orderId",order.id)
			window.location='#/orderdetails'
		}
		$scope.showButtonForChangeStaus = function(order){
			
			if(order.status == 'NEW'){
				return true;
			}
			else{
				return false;
			}
			
			
		}
		
		$scope.changeOrderStatus = function(orderId){
			var url = 'http://localhost:8080/user/changeOrderStatusToRTD?orderId='+orderId;
			$http({
				url: url,
				method: 'GET'
			}).then(function(resp){
				alert(resp.data.message);
				$scope.reloadPage();
			},function(){
				console.log("ERROR");
			})
		}
		$scope.changeOrderStatusToClose = function(orderId){
			var url = 'http://localhost:8080/user/changeOrderStatusToClose?userId='+localStorage.getItem("id")+"&orderId="+orderId;
			$http({
				url: url,
				method: 'GET'
			}).then(function(resp){
				alert(resp.data.message);
				$scope.reloadPage();
			},function(){
				console.log("ERROR");
			})
		}
		
		$scope.addOrderToDelete = function(order){
			console.log(order);
			$scope.orderIdToDelete = order.id;
			$scope.customerid = order.customer.id;
		}
		$scope.deleteOrder = function(){
			var url = 'http://localhost:8080/user/deleteOrder?userId='+localStorage.getItem("id")+'&customerId='+$scope.customerid+'&orderId='+$scope.orderIdToDelete;
			$http({
				url: url,
				method: 'GET'
			}).then(function(resp){
				alert(resp.data.message);
				$scope.reloadPage();
			},function(){
				console.log("ERROR");
			})
		}
		
		$scope.reloadPage = function(){
			var url = 'http://localhost:8080/user/getOrdersByUserId?userId='+localStorage.getItem("id");
			$http({
				url: url,
				method: 'GET'
			}).then(function(resp){
				$scope.orders = resp.data;
			},function(){
				console.log("ERROR");
			})
		}
		$scope.SortData = function(){
			var url = 'http://localhost:8080/user/sortData?userId='+localStorage.getItem("id")+'&attr='+$scope.selectedParam+'&entity=order';
			$http({
				url: url,
				method: 'GET'
			}).then(function(resp){
				$scope.orders = resp.data;
			},function(){
				$log.log("ERROR");
			})
		}
}])