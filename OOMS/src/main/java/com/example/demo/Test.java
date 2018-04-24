
package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Isolation;

import com.example.bo.OrderItem;
import com.example.bo.PurchaseOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Transactional

public class Test {
	
	public static void main(String[] args) throws JsonProcessingException {
		Isolation l;
		
		/*Supplier supplier = new Supplier();
		supplier.setEmail("npnitinpawar47@gmail.com");
		supplier.setName("Ajay Dhawale");
		supplier.setPhone("9856214758");
		
		Address a = new Address();
		a.setStreet("Shivaji road");
		a.setCity("Sinnar");
		a.setState("Maharastra");
		a.setPincode("422103");
		
		supplier.setAddress(a);
		
		System.out.println(new ObjectMapper().writeValueAsString(supplier));*/
		
		PlatformTransactionManager  d;
		
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setOrderDate(new Date());
		purchaseOrder.setTotalCost(4500);
		
		
		
		OrderItem item = new OrderItem();
		item.setCount(10);
		item.setItemId(1);
		
		List<OrderItem> items = new ArrayList<OrderItem>();
		items.add(item);
		
		purchaseOrder.setItems(items);
		
		System.out.println(new ObjectMapper().writeValueAsString(purchaseOrder));
		
        
	}

}
