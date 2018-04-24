package com.example.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="OOMS_PRODUCTITEM")
public class ProductItem
{
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="ID")
  private int id;
  
  @Column(name="ITEMNAME")
  private String name;
  
  @Column(name="DESCRIPTION")
  private String description;
  
  @Column(name="BRAND")
  private String brand;
  
  @Column(name="PRICE")
  private double price;
  
  @Column(name="IMAGEURL")
  private String imageurl;
  
  @Column(name="UOM")
  private String uOM;
  
  @Column(name="STATUS")
  private String status;
  
  @Column(name = "QUANTITY")
  @ColumnDefault("'100'")
  private int quantity;
  
  public int getId()
  {
    return id;
  }
  
  public void setId(int id) { this.id = id; }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) { this.name = name; }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) { this.description = description; }
  
  public String getBrand() {
    return brand;
  }
  
  public void setBrand(String brand) { this.brand = brand; }
  
  public double getPrice() {
    return price;
  }
  
  public void setPrice(double price) { this.price = price; }
  
  public String getImageurl() {
    return imageurl;
  }
  
  public void setImageurl(String imageurl) { this.imageurl = imageurl; }
  
  public String getUOM() {
    return uOM;
  }
  
  public void setUOM(String uOM) { this.uOM = uOM; }
 

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}
  
  
}
