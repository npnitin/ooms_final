package com.example.dto;

import java.util.Set;

import com.example.bo.Address;

public class CustomerDto {


	 
	  private int id;
	  
	
	  private String name;
	  
	 
	  private String email;
	  
	 
	  private String mobile;
	  
	  
	  private String onboardDate;
	  
	 
	  private Address address;
	  
	 
	  private Set<OrderDto> orders;
	  
	  
	  
	  public CustomerDto() {}
	  
	  public int getId()
	  {
	    return id;
	  }
	  
	  public void setId(int id) { this.id = id; }
	  
	  public String getName() {
	    return name;
	  }
	  
	  public void setName(String name) { this.name = name; }
	  
	  public String getEmail() {
	    return email;
	  }
	  
	  public void setEmail(String email) { this.email = email; }
	  
	  public String getMobile() {
	    return mobile;
	  }
	  
	  public void setMobile(String mobile) { this.mobile = mobile; }
	  
	  public Address getAddress() {
	    return address;
	  }
	  
	  public void setAddress(Address address) { this.address = address; }
	  
	  public Set<OrderDto> getOrders() {
	    return orders;
	  }
	  
	  public void setOrders(Set<OrderDto> orders) { this.orders = orders; }

	public String getOnboardDate() {
		return onboardDate;
	}

	public void setOnboardDate(String onboardDate) {
		this.onboardDate = onboardDate;
	}
	  
	  

}
