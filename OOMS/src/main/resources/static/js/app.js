var app = angular.module('oomsApp', ['ngRoute','ngResource']);
app.config(function($routeProvider){
    $routeProvider
        .when('/catalog',{
            templateUrl: 'catalog.html',
            controller: 'catalogController'
        })
        .when('/orders',{
            templateUrl: 'order.html',
            controller: 'orderController'
        })
        .when('/customers',{
            templateUrl: 'customer.html',
            controller: 'customerController'
        })
        .when('/suppliers',{
            templateUrl: 'suppliers.html',
            controller: 'supplierController'
        })
        .when('/supplierorders',{
            templateUrl: 'supplierorders.html',
            controller: 'supplierOrderController'
        })
        .when('/home',{
            templateUrl: 'index.html',
            controller: 'controller1'
        })
        .when('/neworder',{
            templateUrl: 'neworder.html',
            controller: 'neworderController'
        })
        .when('/confirmorder',{
            templateUrl: 'confirmorder.html',
            controller: 'neworderController'
        })
        .when('/orderdetails',{
            templateUrl: 'orderdetails.html',
            controller: 'orderdetailsController'
        })
        .when('/customerdetails',{
            templateUrl: 'customerDetail.html',
            controller: 'customerDetailController'
        })
        .when('/home',{
            templateUrl: 'home.html',
            controller: 'homeController'
        })
        .when('/configuration',{
            templateUrl: 'configuration.html',
            controller: 'configurationController'
        })
        .when('/contact',{
            templateUrl: 'ContactForm.html',
            controller: 'contactController'
        })
        .when('/purchaseOrders',{
            templateUrl: 'purchaseOrder.html',
            controller: 'purchaseOrderController'
        })
         .when('/newPurchaseOrder',{
            templateUrl: 'newPurchaseOrder.html',
            controller: 'newPurchaseOrderController'
        })
        .when('/confirmPurchaseOrder',{
            templateUrl: 'confirmPurchaseOrder.html',
            controller: 'confirmPurchaseOrderController'
        })
        .when('/supplierDetails',{
            templateUrl: 'supplierDetails.html',
            controller: 'supplierDetailsController'
        })
        .when('/purchaseOrderDetails',{
            templateUrl: 'purchaseOrderDetails.html',
            controller: 'purchaseOrderDetailsController'
        })
        .when('/notifications',{
            templateUrl: 'notifications.html',
            controller: 'notificationController'
        })
        .when('/profile',{
            templateUrl: 'profile.html',
            controller: 'profileController'
        })
        .when('/taxes',{
            templateUrl: 'tax.html',
            controller: 'taxController'
        })
        
});
app.controller('catalogController',['$scope',function($scope){
	
}]);