package com.example.dto;

import java.util.List;

import com.example.bo.Customer;
import com.example.bo.OrderItem;
import com.example.bo.Tax;

public class OrderDto {

	
	
	  private int id;
	  
	 
	  private List<OrderItemDto> items;
	  
	 
	  private double totalCost;
	  
	 
	  private double advanceAmount;
	  
	 
	  private double remainingbalance;
	  
	
	  private String dateOfOrder;
	  
	  private Customer customer;
	  
	  private String status;
	  
	  private Tax tax;
	  
	  public OrderDto() {}
	  
	  public int getId()
	  {
	    return id;
	  }
	  
	  public void setId(int id) { this.id = id; }
	  
	  public double getTotalCost()
	  {
	    return totalCost;
	  }
	  
	  public void setTotalCost(double totalCost) { this.totalCost = totalCost; }
	  
	  public double getAdvanceAmount() {
	    return advanceAmount;
	  }
	  
	  public void setAdvanceAmount(double advanceAmount) { this.advanceAmount = advanceAmount; }
	  
	  public double getRemainingbalance() {
	    return remainingbalance;
	  }
	  
	  public void setRemainingbalance(double remainingbalance) { this.remainingbalance = remainingbalance; }
	  
	  public String getDateOfOrder() {
	    return dateOfOrder;
	  }
	  
	  public void setDateOfOrder(String dateOfOrder) { this.dateOfOrder = dateOfOrder; }

	

	public List<OrderItemDto> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDto> items) {
		this.items = items;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Tax getTax() {
		return tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}

	

	
	  
}
