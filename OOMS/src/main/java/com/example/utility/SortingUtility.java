package com.example.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bo.Customer;
import com.example.bo.Order;
import com.example.bo.PurchaseOrder;
import com.example.bo.Supplier;
import com.example.dao.UserRepository;
import com.example.dto.CustomerDto;
import com.example.dto.OrderDto;
import com.example.dto.PurchaseOrderDto;
import com.example.dto.SupplierDto;
import com.example.service.OrderService;
import com.example.service.UserService;

@Component("sortingUtility")
public class SortingUtility {
	
	@Autowired 
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	PurchaseOrderToPurchaseOrderDtoConvertor purchaseOrderToPurchaseOrderDtoConvertor;
	
	public <T> List<T> sortData(String attr,String entity,String userId){
		
		int uid = Integer.parseInt(userId);
		
		if(entity.equalsIgnoreCase("Customer")) {
			
			List<Customer> customers = userService.getCustomersByUserId(userId);
			List<CustomerDto> customerDtos = new ArrayList<>();
			if(attr.equalsIgnoreCase("sortByOnboardDate Desc")) {
				Collections.sort(customers, new SortCustomerByOnboradDateAscComparator());	
			}
			else if(attr.equalsIgnoreCase("sortByOnboardDate Asc")) {

				Collections.sort(customers, new SortCustomerByOnboradDateDescComparator());
			}
			for (Customer customer : customers) {
				customerDtos.add(userService.convertCustomerToCustomerDto(customer));
			}
			return (List<T>) customerDtos;
		}
		
		else if(entity.equalsIgnoreCase("supplier")) {
			
			List<Supplier> suppliers = userRepository.findOne(uid).getSuppliers();
			List<SupplierDto> supplierDtos = new ArrayList<>();
			if(attr.equalsIgnoreCase("sortByOnboardDate Desc")) {
				Collections.sort(suppliers, new SortSupplierByOnboradDateDescComparator());
			}
			else if(attr.equalsIgnoreCase("sortByOnboardDate Asc")) {
				Collections.sort(suppliers, new SortSupplierByOnboradDateAscComparator());
			}
			for (Supplier supplier : suppliers) {
				supplierDtos.add(userService.converSuppliertToSupplierDto(supplier));
			}
			return (List<T>) supplierDtos;
		}
		else if(entity.equalsIgnoreCase("order")) {
			 List<Order> orders = new ArrayList<>();
			
			 List<Customer> customers= userRepository.findOne(uid).getCustomers();
			 for (Customer customer : customers) {
				orders.addAll(customer.getOrders());
			}
			 if(attr.equalsIgnoreCase("sortByOrderDate Desc")) {
				 Collections.sort(orders, new SortOrderByOrderDateDesc());
			 }
			 else if(attr.equalsIgnoreCase("sortByOrderDate Asc")) {
				 Collections.sort(orders, new SortOrderByOrderDateAsc());
			 }
			 List<OrderDto> orderDtos = new ArrayList<>(orders.size());
			 for(Order order : orders) {
				 orderDtos.add(orderService.convertoToOrderDto(order));
			 }
			return (List<T>) orderDtos;
		}
		else if(entity.equalsIgnoreCase("purchaseorder")) {
			List<PurchaseOrder> purchaseOrders = new ArrayList<>();
			List<Supplier> suppliers = userRepository.findOne(uid).getSuppliers();
			for(Supplier supplier : suppliers) {
				purchaseOrders.addAll(supplier.getOrders());
			}
			if(attr.equalsIgnoreCase("sortByPurchaseOrderDate Desc")) {
				Collections.sort(purchaseOrders, new SortPurchaseOrderByOrderDateDesc());
			}
			else if(attr.equalsIgnoreCase("sortByPurchaseOrderDate Asc")) {
				Collections.sort(purchaseOrders, new SortPurchaseOrderByOrderDateAsc());
			}
			List<PurchaseOrderDto> purchaseOrderDtos = new ArrayList<>(purchaseOrders.size());
			for(PurchaseOrder purchaseOrder : purchaseOrders) {
				purchaseOrderDtos.add(purchaseOrderToPurchaseOrderDtoConvertor.convert(purchaseOrder));
			}
			return (List<T>) purchaseOrderDtos;
		}
		
		return null;
	}

}
