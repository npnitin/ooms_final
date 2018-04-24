package com.example.dto;

import java.util.List;

import com.example.bo.Customer;
import com.example.bo.Order;
import com.example.bo.Supplier;

public class HomeDto {
	
	private List<CustomerDto> todayscustomers;
	private List<OrderDto> todaysorders;
	private List<SupplierDto> todayssuppliers;
	private List<PurchaseOrderDto> todaysSupplierseOrders;
	
	private int totalCustomers;
	private int totalSuppliers;
	private int totalOrders;
	private int totalSupplierOrders;
	private int unreadNotofications;
	
	public List<CustomerDto> getTodayscustomers() {
		return todayscustomers;
	}
	public void setTodayscustomers(List<CustomerDto> todayscustomers) {
		this.todayscustomers = todayscustomers;
	}
	public List<OrderDto> getTodaysorders() {
		return todaysorders;
	}
	public void setTodaysorders(List<OrderDto> todaysorders) {
		this.todaysorders = todaysorders;
	}
	public List<SupplierDto> getTodayssuppliers() {
		return todayssuppliers;
	}
	public void setTodayssuppliers(List<SupplierDto> todayssuppliers) {
		this.todayssuppliers = todayssuppliers;
	}
	public int getTotalCustomers() {
		return totalCustomers;
	}
	public void setTotalCustomers(int totalCustomers) {
		this.totalCustomers = totalCustomers;
	}
	public int getTotalSuppliers() {
		return totalSuppliers;
	}
	public void setTotalSuppliers(int totalSuppliers) {
		this.totalSuppliers = totalSuppliers;
	}
	public int getTotalOrders() {
		return totalOrders;
	}
	public void setTotalOrders(int totalOrders) {
		this.totalOrders = totalOrders;
	}
	public List<PurchaseOrderDto> getTodaysSupplierseOrders() {
		return todaysSupplierseOrders;
	}
	public void setTodaysSupplierseOrders(List<PurchaseOrderDto> todaysSupplierseOrders) {
		this.todaysSupplierseOrders = todaysSupplierseOrders;
	}
	public int getTotalSupplierOrders() {
		return totalSupplierOrders;
	}
	public void setTotalSupplierOrders(int totalSupplierOrders) {
		this.totalSupplierOrders = totalSupplierOrders;
	}
	public int getUnreadNotofications() {
		return unreadNotofications;
	}
	public void setUnreadNotofications(int unreadNotofications) {
		this.unreadNotofications = unreadNotofications;
	}
	
	
	

}
