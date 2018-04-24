package com.example.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OOMS_ADDRESS")
public class Address
{
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="ID")
  private int id;
  
  @Column(name="street")
  private String street;
  
  @Column(name="city")
  private String city;
  
  @Column(name="state")
  private String state;
  
  @Column(name="pincode")
  private String pincode;
  
  public Address() {}
  
  public int getId()
  {
    return id;
  }
  
  public void setId(int id) { this.id = id; }
  
  public String getStreet() {
    return street;
  }
  
  public void setStreet(String street) { this.street = street; }
  
  public String getCity() {
    return city;
  }
  
  public void setCity(String city) { this.city = city; }
  
  public String getState() {
    return state;
  }
  
  public void setState(String state) { this.state = state; }
  
  public String getPincode() {
    return pincode;
  }
  
  public void setPincode(String pincode) { this.pincode = pincode; }
}
