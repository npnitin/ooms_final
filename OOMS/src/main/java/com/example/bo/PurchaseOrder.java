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
@Table(name = "OOMS_PURCHASE_ORDER")
public class PurchaseOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	
	@Column(name = "orderDate")
	private Date orderDate;
	
	@Column(name="TOTALCOST")
	private double totalCost;
	
	@Column(name = "status")
	private String status;
	
	@OneToMany(cascade={javax.persistence.CascadeType.ALL})
	@JoinTable(name="OOMS_PURCHASE_ORDER_ITEMS", joinColumns={@javax.persistence.JoinColumn(name="PURCHASE_ORDER_ID", referencedColumnName="ID")}, inverseJoinColumns={@javax.persistence.JoinColumn(name="ORDERITEM_ID", referencedColumnName="ID")})
	private List<OrderItem> items;
	
	@ManyToOne
	Supplier supplier;
	
	@OneToOne
	private Tax tax;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Tax getTax() {
		return tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}
	
	
	
	
	
	
}
