app.controller('catalogController',['$scope','$http',function($scope,$http){
	$scope.catalog={};
	$scope.updateItem={};
	//this should gets executed on page load
		var url = 'http://localhost:8080/user/getCatalogByUserId?userId='+localStorage.getItem("id");
		$http({
			url: url,
			method: 'GET'
		}).then(function(resp){
			$scope.catalog = resp.data;
		},function(){
			$log.log("ERROR");
		})
	
		$scope.addItem = function(){
			var ProductItem={
					name : $scope.iname,
					description : $scope.idescription,
					brand : $scope.ibrand,
					price : $scope.iprice,
					uom : $scope.iuom,
					stauts : $scope.istatus,
					quantity : $scope.iquantity
			}
			var config = {
					headers:{
						'Content-Type': 'application/json'
					},
				};
			var url = "http://localhost:8080/user/addItemToCatalog?userId="+localStorage.getItem("id");
			var data = ProductItem;
			$http.post(url,data,config).success(function(data,status,headers,config){
				
				if(data.errorCode == "200"){
					document.getElementById("newItemFrm").reset();
					$scope.reloadPage();
					
				}
				
			}).error(function(data,status,headers,config){
				console.log('ERROR');
			});
			
		}
		
		$scope.deleteItem = function(itemId){
			
			var url = 'http://localhost:8080/user/deleteItemFromCatalog?userId='+localStorage.getItem("id")+"&itemId="+itemId;
			$http({
				url: url,
				method: 'GET'
			}).then(function(resp){
				console.log("success");
				alert("Item deactivated succesfully");	
				$scope.reloadPage();
			},function(){
				$log.log("ERROR");
			})
			
		}
		$scope.updateItemfun = function(item){
			$scope.updateItem = item;
		}
		
		$scope.showButtonForDelete = function(item){	
			if(item.stauts.toUpperCase() == 'ACTIVE'){
				return true;
			}
			else{
				return false;
			}
				
		}
		$scope.updateItemServer = function(){
			var ProductItem={
					name : $scope.updateItem.name,
					description : $scope.updateItem.description,
					brand : $scope.updateItem.brand,
					price : $scope.updateItem.price,
					uom : $scope.updateItem.uom,
					stauts : $scope.updateItem.status
			}
			var config = {
					headers:{
						'Content-Type': 'application/json'
					},
				};
			var url = "http://localhost:8080/user/updateItemFromCatalog?userId="+localStorage.getItem("id")+"&itemId="+$scope.updateItem.id;
			var data = ProductItem;
			$http.post(url,data,config).success(function(data,status,headers,config){
				
				if(data.errorCode == "200"){
					document.getElementById("ItemUpdateFrm").reset();
					$scope.reloadPage();
					
				}
				
			}).error(function(data,status,headers,config){
				console.log('ERROR');
			});
		}
		
		$scope.reloadPage = function(){
			var url = 'http://localhost:8080/user/getCatalogByUserId?userId='+localStorage.getItem("id");
			$http({
				url: url,
				method: 'GET'
			}).then(function(resp){
				$scope.catalog = resp.data;
			},function(){
				$log.log("ERROR");
			})
		}
}])