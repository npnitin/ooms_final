package com.example.bo;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="OOMS_ORDER")
public class Order
{
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="ID")
  private int id;
  
  @OneToMany(cascade={javax.persistence.CascadeType.ALL})
  @JoinTable(name="OOMS_ORDER_ITEMS", joinColumns={@javax.persistence.JoinColumn(name="ORDER_ID", referencedColumnName="ID")}, inverseJoinColumns={@javax.persistence.JoinColumn(name="ORDERITEM_ID", referencedColumnName="ID")})
  private List<OrderItem> items;
  
  @Column(name="TOTALCOST")
  private double totalCost;
  
  @Column(name="ADVANCEAMOUNT")
  private double advanceAmount;
  
  @Column(name="REMAININGBALANCE")
  private double remainingbalance;
  
  @Column(name="DATEOFORDER")
  private Date dateOfOrder;
  
  @Column(name="STATUS")
  private String status;
  
  @ManyToOne(cascade={javax.persistence.CascadeType.ALL})
  private Customer customer;
  
  @OneToOne
  private Tax tax;
  
  
  public Tax getTax() {
	return tax;
}

public void setTax(Tax tax) {
	this.tax = tax;
}

public Order() {}
  
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
  
  public Date getDateOfOrder() {
    return dateOfOrder;
  }
  
  public void setDateOfOrder(Date dateOfOrder) { this.dateOfOrder = dateOfOrder; }

public List<OrderItem> getItems() {
	return items;
}

public void setItems(List<OrderItem> items) {
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

  
}
