app.controller('newPurchaseOrderController',['$scope','$http',function($scope,$http){
	$scope.suppliers =[];
	$scope.selectedSupplierForAutoComplete;
	$scope.localItems = [];
	$scope.localCounterForItemId=0;
	$scope.supplier={};
	
	var url = 'http://localhost:8080/user/getAllSuppliersByUserId?userId='+localStorage.getItem("id");
	$http({
		url: url,
		method: 'GET'
	}).then(function(resp){
		$scope.suppliers = resp.data;
	},function(){
		$log.log("ERROR");
	})
	
		$scope.catalogItems ={};
		//load all items on page load to show in drop down auto suggestion
			var url = 'http://localhost:8080/user/getCatalogByUserId?userId='+localStorage.getItem("id");
			$http({
				url: url,
				method: 'GET'
			}).then(function(resp){
				$scope.catalogItems = resp.data.items;
			},function(){
				$log.log("ERROR");
			})

	$scope.call = function(){
		$scope.posname = $scope.selectedSupplierForAutoComplete.name;
		$scope.posemail = $scope.selectedSupplierForAutoComplete.email;
		$scope.posphone = $scope.selectedSupplierForAutoComplete.phone;
		$scope.posstreet = $scope.selectedSupplierForAutoComplete.address.street;
		$scope.poscity = $scope.selectedSupplierForAutoComplete.address.city;
		$scope.posstate = $scope.selectedSupplierForAutoComplete.address.state;
		$scope.pospincode = $scope.selectedSupplierForAutoComplete.address.pincode;
		$scope.tempemail = $scope.selectedSupplierForAutoComplete.email;
		$scope.tempphone = $scope.selectedSupplierForAutoComplete.mobile;
		
		document.getElementById("sname").disabled = true;
		document.getElementById("semail").disabled = true;
		document.getElementById("sphone").disabled = true;
		document.getElementById("sstreet").disabled = true;
		document.getElementById("scity").disabled = true;
		document.getElementById("sstate").disabled = true;
		document.getElementById("spincode").disabled = true;
	}
	$scope.clearFrm = function(){
				document.getElementById("sname").disabled = false;
				document.getElementById("semail").disabled = false;
				document.getElementById("sphone").disabled = false;
				document.getElementById("sstreet").disabled = false;
				document.getElementById("scity").disabled = false;
				document.getElementById("sstate").disabled = false;
				document.getElementById("spincode").disabled = false;
				document.getElementById("porderFrm").reset();
			}
	$scope.fillValues = function(){
			$scope.iprice = $scope.selectedItem.price;
		}
	
	$scope.addItem = function(){
		var item = $scope.selectedItem;
		item.count = $scope.iquantity;
		item.counter =$scope.localCounterForItemId++;
		item.itemId = item.id;
		item.id=0;
		$scope.localItems.push(item);
	}
	$scope.deleteItem = function(id){
		$scope.localItems.splice(id,1);
		$scope.localCounterForItemId--;
	}
	
	$scope.addSupplierToLocalStorage = function(){
		$scope.supplier.name = $scope.posname;
		$scope.supplier.email = $scope.posemail;
		$scope.supplier.phone = $scope.posphone;
		$scope.supplier.street = $scope.posstreet;
		$scope.supplier.city = $scope.poscity;
		$scope.supplier.state = $scope.posstate;
		$scope.supplier.pincode = $scope.pospincode;
		$scope.supplier.orderitems = $scope.localItems;
		if( $scope.tempemail === $scope.posemail && $scope.tempphone === $scope.posphone){
			$scope.supplier.id = $scope.selectedCustomerForAutoComplete.id;
		}	
		else{
			$scope.supplier.id = 0;
		}
		localStorage.setItem("supplier",JSON.stringify($scope.supplier));
	}
	
}])