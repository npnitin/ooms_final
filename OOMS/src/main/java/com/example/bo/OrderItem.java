package com.example.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OOMS_ORDERITEM")
public class OrderItem
{
  @Id
  @GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
  @Column(name="ID")
  private int id;
  
  @Column(name="PRODUCTITEMID")
  private int itemId;
  
  @Column(name="COUNT")
  private int count;
  
  public OrderItem() {}
  
  public int getId()
  {
    return id;
  }
  
  public void setId(int id) { this.id = id; }
  
  public int getItemId() {
    return itemId;
  }
  
  public void setItemId(int itemId) { this.itemId = itemId; }
  
  public int getCount() {
    return count;
  }
  
  public void setCount(int count) { this.count = count; }
}
