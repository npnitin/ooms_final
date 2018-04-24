app.controller('supplierController',['$scope','$http','$route',function($scope,$http,$route){
	
	$scope.suppliers=[];
	$scope.supplierIdTodelete={};
	$scope.supplierToupdate={};
	
	$scope.sortparams=['sortByOnboardDate Desc','sortByOnboardDate Asc'];
	$scope.selectedParam;
	//this should gets executed on page load
	var url = 'http://localhost:8080/user/getSuppliersByUserId?userId='+localStorage.getItem("id");
	$http({
		url: url,
		method: 'GET'
	}).then(function(resp){
		$scope.suppliers = resp.data;
	},function(){
		console.log("ERROR");
	})
	
	
	$scope.addSupplier = function(){

		var address ={
				street :$scope.sstreet,
				city : $scope.scity,
				state : $scope.sstate,
				pincode : $scope.spincode
				
		}
		var supplier={
				name : $scope.sname,
				email : $scope.semail,
				phone : $scope.sphone,
				address : address
		}
		var config = {
				headers:{
					'Content-Type': 'application/json'
				},
			};
		var url = 'http://localhost:8080/user/addSupplier?userId='+localStorage.getItem("id");
		var data = supplier;
		$http.post(url,data,config).success(function(data,status,headers,config){
			
			if(data.errorCode == "200"){
				console.log("Success");
				$scope.reloadPage();
			}
			
		}).error(function(data,status,headers,config){
			console.log('ERROR');
		});
		
	}
	
	$scope.addSupplierTodelete = function(supplier){
		$scope.supplierIdTodelete = supplier.id;
	}
	$scope.deleteSupplierServer = function(){
		var url = 'http://localhost:8080/user/deleteSupplier?userId='+localStorage.getItem("id")+"&supplierId="+$scope.supplierIdTodelete;
		$http({
			url: url,
			method: 'GET'
		}).then(function(resp){
			console.log(resp.data);
			$scope.reloadPage();
		},function(){
			console.log("ERROR");
		})
	}
	$scope.reloadPage = function(){
		var url = 'http://localhost:8080/user/getSuppliersByUserId?userId='+localStorage.getItem("id");
		$http({
			url: url,
			method: 'GET'
		}).then(function(resp){
			$scope.suppliers = resp.data;
		},function(){
			console.log("ERROR");
		})
	}
	
	$scope.updateSupplier = function(supplier){
		console.log(supplier);
		$scope.supplierToupdate = supplier;
		$scope.usname = supplier.name;
		$scope.usemail = supplier.email;
		$scope.usphone = supplier.phone;
		$scope.usstreet = supplier.address.street;
		$scope.uscity = supplier.address.city;
		$scope.usstate = supplier.address.state;
		$scope.uspincode = supplier.address.pincode;
	}
	$scope.updateSupplierServer = function(){


		
		var address ={
				street : $scope.usstreet,
				city : $scope.uscity,
				state : $scope.usstate,
				pincode : $scope.uspincode
				
		}
		var supplier={
				name : $scope.usname,
				email : $scope.usemail,
				phone : $scope.usphone,
				address : address
		}
		var config = {
				headers:{
					'Content-Type': 'application/json'
				},
			};
		var url = "http://localhost:8080/user/updateSupplier?userId="+localStorage.getItem("id")+"&supplierId="+$scope.supplierToupdate.id;
		var data = supplier;
		$http.post(url,data,config).success(function(data,status,headers,config){
			
			if(data.errorCode == "200"){
				$scope.reloadPage();
			}
			
		}).error(function(data,status,headers,config){
			console.log('ERROR');
		});
		
		$scope.reloadPage();
		
	
		
	}
	
	$scope.viewSupplierDetails = function(supplier){
		console.log(supplier);
		localStorage.setItem("viewsupplierdetailsId",supplier.id);
		window.location='#/supplierDetails'
	}
	$scope.SortData = function(){
		var url = 'http://localhost:8080/user/sortData?userId='+localStorage.getItem("id")+'&attr='+$scope.selectedParam+'&entity=supplier';
		$http({
			url: url,
			method: 'GET'
		}).then(function(resp){
			$scope.suppliers = resp.data;
		},function(){
			$log.log("ERROR");
		})
	}
	
}])