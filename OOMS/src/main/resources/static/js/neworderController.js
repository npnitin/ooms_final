app.controller('neworderController',['$scope','$http',function($scope,$http){
		$scope.selectedItem={};
		$scope.selectedCustomerForAutoComplete;
		$scope.localItems = [];
		$scope.tax={};
		$scope.localCounterForItemId=0;
		$scope.customer={};
		
		$scope.totalamt = 0;
		$scope.tempemail;
		$scope.tempphone;
		
		//for autocomplete
		$scope.customers ={};
		//making select2 library eligible
		$(".select2").select2();
		//load all customer data on page load to display customer auto complete
		var url = 'http://localhost:8080/user/getAllCustomersByUserId?userId='+localStorage.getItem("id");
		$http({
			url: url,
			method: 'GET'
		}).then(function(resp){
			$scope.customers = resp.data;
		},function(){
			$log.log("ERROR");
		})
		
		
		
		
		
		
		$scope.Useritems ={};
		//load all items on page load to show in drop down auto suggestion
			var url = 'http://localhost:8080/user/getCatalogByUserId?userId='+localStorage.getItem("id");
			$http({
				url: url,
				method: 'GET'
			}).then(function(resp){
				$scope.Useritems = resp.data.items;
			},function(){
				$log.log("ERROR");
			})
			
			$scope.fillValues = function(){
				$scope.iprice = $scope.selectedItem.price;
			}
			$scope.addItem = function(){
				if($scope.iquantity <= $scope.selectedItem.quantity){
					var item = $scope.selectedItem;
					item.count = $scope.iquantity;
					item.counter =$scope.localCounterForItemId++;
					item.itemId = item.id;
					item.id=0;
					$scope.localItems.push(item);
					$scope.fetchTaxes();
				}
				else{
					alert("only "+$scope.selectedItem.quantity +" items left for "+$scope.selectedItem.name);
				}
				
			}
			$scope.deleteItem = function(id){
				$scope.localItems.splice(id,1);
				$scope.localCounterForItemId--;
			}
			
			$scope.addDataToLocalStorage = function(){
				$scope.customer.name = $scope.ocname;
				$scope.customer.email = $scope.ocemail;
				$scope.customer.phone = $scope.ocphone;
				$scope.customer.street = $scope.ocstreet;
				$scope.customer.city = $scope.occity;
				$scope.customer.state = $scope.ocstate;
				$scope.customer.pincode = $scope.ocpincode;
				$scope.customer.orderitems = $scope.localItems;
				$scope.customer.tax =  $scope.selectedTax;
				if( $scope.tempemail === $scope.ocemail && $scope.tempphone === $scope.ocphone){
					$scope.customer.id = $scope.selectedCustomerForAutoComplete.id;
				}	
				else{
					$scope.customer.id = 0;
				}
				//check for item quantity
				
				console.log("details:"+JSON.stringify($scope.customer));
				localStorage.setItem("customer",JSON.stringify($scope.customer));
			}
			
		$scope.call = function(){
			$scope.ocname = $scope.selectedCustomerForAutoComplete.name;
			$scope.ocemail = $scope.selectedCustomerForAutoComplete.email;
			$scope.ocphone = parseInt($scope.selectedCustomerForAutoComplete.mobile);
			$scope.ocstreet = $scope.selectedCustomerForAutoComplete.address.street;
			$scope.occity = $scope.selectedCustomerForAutoComplete.address.city;
			$scope.ocstate = $scope.selectedCustomerForAutoComplete.address.state;
			$scope.ocpincode = parseInt($scope.selectedCustomerForAutoComplete.address.pincode);
			
			
			$scope.tempemail = $scope.selectedCustomerForAutoComplete.email;
			$scope.tempphone = $scope.selectedCustomerForAutoComplete.mobile;
			document.getElementById("cname").disabled = true;
			document.getElementById("cemail").disabled = true;
			document.getElementById("cphone").disabled = true;
			document.getElementById("cstreet").disabled = true;
			document.getElementById("ccity").disabled = true;
			document.getElementById("cstate").disabled = true;
			document.getElementById("cpincode").disabled = true;
		}
		$scope.clearFrm = function(){
			document.getElementById("cname").disabled = false;
			document.getElementById("cemail").disabled = false;
			document.getElementById("cphone").disabled = false;
			document.getElementById("cstreet").disabled = false;
			document.getElementById("ccity").disabled = false;
			document.getElementById("cstate").disabled = false;
			document.getElementById("cpincode").disabled = false;
			document.getElementById("orderFrm").reset();
		}
		$scope.fetchTaxes = function(){
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
		$scope.fillTaxValues = function(){
			$scope.tax = $scope.selectedTax;
			$scope.selectedTax.creationDate=null;
			$scope.selectedTax.updatedDate=null;
		}
		
		$scope.previewOrder = function(){
			$scope.taxAmt = 0;
			$scope.totalamt = 0;
			$scope.finalAmt = 0;
			for(var i=0;i< $scope.localItems.length;i++){
				$scope.totalamt = parseInt($scope.totalamt) + (parseInt($scope.localItems[i].price) * parseInt($scope.localItems[i].count));
			}
			$scope.taxAmt = parseInt($scope.totalamt) * ($scope.selectedTax.percentage/100);
			$scope.finalAmt = parseInt($scope.totalAmt) + parseInt($scope.taxAmt);
			
		}
}])