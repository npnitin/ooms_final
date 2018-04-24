app.controller('taxController',['$scope','$http',function($scope,$http){
	
	$scope.taxes=[];
	$scope.tax={};
	
	var url = 'http://localhost:8080/user/getAllTaxesByUserId?userId='+localStorage.getItem("id");
	$http({
		url: url,
		method: 'GET'
	}).then(function(resp){
		$scope.taxes = resp.data;
	},function(){
		console.log("ERROR");
	})
	
	
	$scope.addTax = function(){

	
		var tax={
				name : $scope.tname,
				description : $scope.tdescription,
				percentage : $scope.tpercentage
		}
		var config = {
				headers:{
					'Content-Type': 'application/json'
				},
			};
		var url = "http://localhost:8080/user/addTaxByUser?userId="+localStorage.getItem("id");
		var data = tax;
		$http.post(url,data,config).success(function(data,status,headers,config){
			
			if(data.errorCode == "200"){
				alert("Tax added succesfully");
				$scope.reloadPage();
			}
			else{
				alert(data.message);
			}
			
		}).error(function(data,status,headers,config){
			alert("Error while adding tax data");
		});
		
	
	}
	
	$scope.reloadPage = function(){
		var url = 'http://localhost:8080/user/getAllTaxesByUserId?userId='+localStorage.getItem("id");
		$http({
			url: url,
			method: 'GET'
		}).then(function(resp){
			$scope.taxes = resp.data;
		},function(){
			console.log("ERROR");
		})
	}
	
	$scope.addTaxToDelete = function(tax){
		$scope.tax = tax;
	}
	$scope.deleteTaxServer = function(){
		var url = 'http://localhost:8080/user/deleteTaxById?userId='+localStorage.getItem("id")+"&taxId="+$scope.tax.id;
		$http({
			url: url,
			method: 'GET'
		}).then(function(resp){
			$scope.taxes = resp.data;
			$scope.reloadPage();
		},function(){
			console.log("ERROR");
		})
	}
	
	$scope.updateTax = function(){
		var tax={
				name : $scope.utname,
				description : $scope.utdescription,
				percentage : $scope.utpercentage,
		}
		var config = {
				headers:{
					'Content-Type': 'application/json'
				},
			};
		var url = "http://localhost:8080/user/updateTaxById?userId="+localStorage.getItem("id")+"&taxId="+$scope.tax.id;
		var data = tax;
		$http.post(url,data,config).success(function(data,status,headers,config){
			
			if(data.errorCode == "200"){
				alert("Tax updated succesfully");
				$scope.reloadPage();
			}
			else{
				alert(data.message);
			}
			
		}).error(function(data,status,headers,config){
			alert("Error while updating tax data");
		});
		
	
	
	}
	
	$scope.addTaxToUpdate = function(tax){
		$scope.tax = tax;
		$scope.utname = tax.name;
		$scope.utdescription = tax.description;
		$scope.utpercentage = tax.percentage;
	}
	
}])