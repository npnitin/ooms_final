package com.example.bo;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="OOMS_SUPPLIER")
public class Supplier
{
	
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="ID")
  private int id;
  
  @Column(name="SUPP_NAME")
  private String name;
  
  @Column(name="EMAIL")
  private String email;
  
  @Column(name="PHONE")
  private String phone;
  
  @Column(name="ONBOARDDATE")
  private Date onboardDate;
  
  @OneToOne(cascade= {CascadeType.ALL})
  @JoinColumn(name="ADDRESS_ID")
  private Address address;
  
  @JsonIgnore
  @OneToMany(cascade={javax.persistence.CascadeType.ALL})
  @JoinTable(name="OOMS_SUPPLIERS_ORDERS", joinColumns={@JoinColumn(name="SUPPLIER_ID", referencedColumnName="ID")}, inverseJoinColumns={@JoinColumn(name="ORDER_ID", referencedColumnName="ID")})
  private Set<PurchaseOrder> orders;
  
  public Supplier() {}
  
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

public Date getOnboardDate() {
	return onboardDate;
}

public void setOnboardDate(Date onboardDate) {
	this.onboardDate = onboardDate;
}

public Set<PurchaseOrder> getOrders() {
	return orders;
}

public void setOrders(Set<PurchaseOrder> orders) {
	this.orders = orders;
}
  
  
  
}
