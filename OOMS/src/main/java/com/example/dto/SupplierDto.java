package com.example.dto;

import java.util.Set;

import com.example.bo.Address;

public class SupplierDto {
	

	
	  
	  private int id;
	  
	 
	  private String name;
	  
	 
	  private String email;
	  
	 
	  private String phone;
	  
	 
	  private String onboardDate;
	  
	 
	  private Address address;
	  
	  private Set<PurchaseOrderDto> orders;
	  
	  public SupplierDto() {}
	  
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
	  
	  public String getPhone() {
	    return phone;
	  }
	  
	  public void setPhone(String phone) { this.phone = phone; }
	  
	  public Address getAddress() {
	    return address;
	  }
	  
	  public void setAddress(Address address) { this.address = address; }

	public String getOnboardDate() {
		return onboardDate;
	}

	public void setOnboardDate(String onboardDate) {
		this.onboardDate = onboardDate;
	}

	public Set<PurchaseOrderDto> getOrders() {
		return orders;
	}

	public void setOrders(Set<PurchaseOrderDto> orders) {
		this.orders = orders;
	}
	  
	  
	  


}
