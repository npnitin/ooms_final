app.controller('confirmorderController',['$scope','$http',function($scope,$http){
	
	$scope.customer = JSON.parse(localStorage.getItem("customer"));
	$scope.items = $scope.customer.orderitems;
	
	$scope.ocname = $scope.customer.name;
	$scope.ocemail = $scope.customer.email;
	$scope.ocphone = $scope.customer.phone;
	
	$scope.totalamt = 0;
	$scope.paidamt = 0;
	$scope.balanceamt = 0;
	$scope.customerId = 0;
	
	
	for(var i=0;i< $scope.items.length;i++){
		$scope.totalamt = $scope.totalamt + ($scope.items[i].price*$scope.items[i].count);
	}
	
	$scope.tax = $scope.customer.tax;
	$scope.taxAmt = $scope.totalamt*($scope.tax.percentage/100);
	$scope.balanceamt = $scope.totalamt + $scope.taxAmt;
	$scope.payableAmt = $scope.totalamt + $scope.taxAmt
	
	$scope.calculateBalanceAmt = function(){
		$scope.balanceamt = $scope.payableAmt-$scope.paidamt;
	}
	
	$scope.submitOrder = function(){
		if($scope.customer.id === 0){
			/*We have new customer and order details here.So add customer first and then get that customerid 
			and add order to the customer orders.So make two Ajax requests here.*/
			
			
		//	1)Add customer
			
			var address ={
					street : $scope.customer.street,
					city : $scope.customer.city,
					state : $scope.customer.state,
					pincode : $scope.customer.pincode
			}
			
			var customer ={
					name : $scope.ocname,
					address : address,
					email : $scope.ocemail,
					mobile : $scope.ocphone
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
					$scope.customer.id = data.message;
					//2)now add order
					$scope.addOrder();
				}
				
			}).error(function(data,status,headers,config){
				console.log('ERROR');
			});
			
		}
		else{
			$scope.addOrder();
		}
		
		
	}
	
	$scope.addOrder = function(){
		var tax = $scope.customer.tax;
		tax.creationDate = null;
		tax.updatedDate = null;
		var order ={
				items : $scope.items,
				totalCost : $scope.payableAmt,
				advanceAmount : $scope.paidamt,
				remainingbalance : $scope.balanceamt,
				tax : tax
		}
		
		var config = {
				headers:{
					'Content-Type': 'application/json'
				},
			};
		var url = "http://localhost:8080/user/addOrder?userId="+localStorage.getItem("id")+"&customerId="+$scope.customer.id;
		var data = order;
		$http.post(url,data,config).success(function(data,status,headers,config){
			
			if(data.errorCode == "200"){
				localStorage.removeItem("customer");
				alert("order added succesfulli..!!!!!");
				window.location="#/orders";
			}
			else{
				alert(data.message);
			}
			
		}).error(function(data,status,headers,config){
			
		});
		
	}
	
}])