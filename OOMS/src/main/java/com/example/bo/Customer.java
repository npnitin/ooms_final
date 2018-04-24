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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="OOMS_CUSTOMER")
public class Customer
{
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="ID")
  private int id;
  
  @Column(name="CUSTNAME")
  private String name;
  
  @Column(name="EMAIL")
  private String email;
  
  @Column(name="MOBILE")
  private String mobile;
  
  @Column(name="ONBOARDDATE")
  private Date onboardDate;
  
  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name="ADDRESS_ID")
  private Address address;
  
  @JsonIgnore
  @OneToMany(cascade={javax.persistence.CascadeType.ALL})
  @JoinTable(name="OOMS_CUSTOMER_ORDERS", joinColumns={@JoinColumn(name="CUSTOMER_ID", referencedColumnName="ID")}, inverseJoinColumns={@JoinColumn(name="ORDER_ID", referencedColumnName="ID")})
  private Set<Order> orders;
  
  
  
  public Customer() {}
  
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
  
  public Set<Order> getOrders() {
    return orders;
  }
  
  public void setOrders(Set<Order> orders) { this.orders = orders; }

public Date getOnboardDate() {
	return onboardDate;
}

public void setOnboardDate(Date onboardDate) {
	this.onboardDate = onboardDate;
}
  
  
}
