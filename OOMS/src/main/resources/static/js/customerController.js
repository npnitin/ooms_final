app.controller('customerController',['$scope','$http','$window','$route',function($scope,$http,$window,$route){
	$scope.customers=[];
	$scope.cid=0;
	$scope.sortparams=['sortByOnboardDate Desc','sortByOnboardDate Asc'];
	$scope.selectedParam;
	//this should gets executed on page load
		var url = 'http://localhost:8080/user/getCustomersByUserId?userId='+localStorage.getItem("id");
		$http({
			url: url,
			method: 'GET'
		}).then(function(resp){
			$scope.customers = resp.data;
		},function(){
			alert("Error while fetching customer data from server");
		})
	
		$scope.addCustomer = function(){
			
			var address ={
					street :$scope.cstreet,
					city : $scope.ccity,
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
					alert("Customer added succesfully");
					$scope.reloadPage();
				}
				else{
					alert(data.message);
				}
				
			}).error(function(data,status,headers,config){
				alert("Error while adding customer data");
			});
			
		}
		$scope.viewDetails = function(customerId){
			localStorage.setItem("customerId",customerId);
			window.location='#/customerdetails'
		}
		$scope.deleteCustomer = function(){
			var url = 'http://localhost:8080/user/deleteCustomerByUserIdAndId?userId='+localStorage.getItem("id")+"&customerId="+$scope.cid;
			$http({
				url: url,
				method: 'GET'
			}).then(function(resp){
				$route.reload();
				alert(resp.data.message)
			},function(){
				alert("Error while deleting customer");
			});
			$scope.reloadPage();
		}
		$scope.add = function(cid){
			$scope.cid = cid;
		}
		$scope.reloadPage = function(){
			//reload page
			var url = 'http://localhost:8080/user/getCustomersByUserId?userId='+localStorage.getItem("id");
			$http({
			url: url,
			method: 'GET'
		}).then(function(resp){
			$scope.customers = resp.data;
		},function(){
			$log.log("ERROR");
		})
		}
		
		
		$scope.updateCustomer = function(customer){
			$scope.updatedCustomer = customer;
		}
		$scope.updateCustomerServer = function(){

			
			var address ={
					street :$scope.updatedCustomer.address.street,
					city : $scope.updatedCustomer.address.city,
					state : $scope.updatedCustomer.address.state,
					pincode : $scope.updatedCustomer.address.pincode
					
			}
			var customer={
					name : $scope.updatedCustomer.name,
					email : $scope.updatedCustomer.email,
					mobile : $scope.updatedCustomer.mobile,
					address : address
			}
			var config = {
					headers:{
						'Content-Type': 'application/json'
					},
				};
			var url = "http://localhost:8080/user/updateCustomer?userId="+localStorage.getItem("id")+"&customerId="+$scope.updatedCustomer.id;
			var data = customer;
			$http.post(url,data,config).success(function(data,status,headers,config){
				
				if(data.errorCode == "200"){
					alert(data.message);
					$route.reload();
				}
				
			}).error(function(data,status,headers,config){
				alert("error while updating customer data");
			});
			
			$scope.reloadPage();
			
		}
		$scope.SortData = function(){
			var url = 'http://localhost:8080/user/sortData?userId='+localStorage.getItem("id")+'&attr='+$scope.selectedParam+'&entity=customer';
			$http({
				url: url,
				method: 'GET'
			}).then(function(resp){
				$scope.customers = resp.data;
			},function(){
				$log.log("ERROR");
			})
		}
}])