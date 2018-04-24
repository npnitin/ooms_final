app.controller('confirmPurchaseOrderController',['$scope','$http',function($scope,$http){
	

	$scope.supplier = JSON.parse(localStorage.getItem("supplier"));
	$scope.items = $scope.supplier.orderitems;
	
	$scope.posname = $scope.supplier.name;
	$scope.posemail = $scope.supplier.email;
	$scope.posphone = $scope.supplier.phone;
	
	$scope.totalamt = 0;
	$scope.paidamt = 0;
	$scope.balanceamt = 0;
	$scope.supplierId = 0;
	
	for(var i=0;i< $scope.items.length;i++){
		$scope.totalamt = $scope.totalamt + ($scope.items[i].price*$scope.items[i].count);
	}
	
	
	$scope.calculateBalanceAmt = function(){
		$scope.balanceamt = $scope.totalamt-$scope.paidamt;
	}
	
	$scope.submitOrder = function(){
		if($scope.supplier.id === 0){
			/*We have new supplier and order details here.So add customer first and then get that supplierid 
			and add order to the customer orders.So make two Ajax requests here.*/
			
			
			
		//	1)Add supplier
			
			var address ={
					street : $scope.supplier.street,
					city : $scope.supplier.city,
					state : $scope.supplier.state,
					pincode : $scope.supplier.pincode
			}
			
			var supplier ={
					name : $scope.posname,
					address : address,
					email : $scope.posemail,
					phone : $scope.posphone
			}
			var config = {
					headers:{
						'Content-Type': 'application/json'
					},
				};
			var url = "http://localhost:8080/user/addSupplier?userId="+localStorage.getItem("id");
			var data = supplier;
			$http.post(url,data,config).success(function(data,status,headers,config){
				if(data.errorCode == "200"){
					$scope.supplier.id = data.message;
					//2)now add order
					$scope.addPurchaseOrder();
				}
				
			}).error(function(data,status,headers,config){
				console.log('ERROR');
			});
			
		}
		else{
			$scope.addPurchaseOrder();
		}
		
		
	}
	
	$scope.addPurchaseOrder = function(){
		var order ={
				items : $scope.items,
				totalCost : $scope.totalamt,
				advanceAmount : $scope.paidamt,
				remainingbalance : $scope.balanceamt		
		}
		
		var config = {
				headers:{
					'Content-Type': 'application/json'
				},
			};
		var url = "http://localhost:8080/user/addPurchaseOrder?userId="+localStorage.getItem("id")+"&supplierId="+$scope.supplier.id;
		var data = order;
		$http.post(url,data,config).success(function(data,status,headers,config){
			
			if(data.errorCode == "200"){
				localStorage.removeItem("supplier");
				alert("Purchaseorder added succesfully..!!!!!");
				window.location="#/purchaseOrders";
			}
			
		}).error(function(data,status,headers,config){
			
		});
		
	}
	

}])