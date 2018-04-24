package com.example.bo;

import java.util.Date;
import java.util.List;

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


@Entity
@Table(name="OOMS_USER")
public class User
{
	
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="ID")
  private int id;
  
  @Column(name="USER_NAME")
  private String name;
  
  @Column(name="EMAIL")
  private String email;
  
  @Column(name="PHONE")
  private String phone;
  
  @Column(name="PASSWORD")
  private String password;
  
  @Column(name="DATEOFREGISTRATION")
  private Date dateOfRegistration;
  
  @Column(name = "STATUS")
  private int status;
  
  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name="ADDRESS_ID")
  private Address address;
  
  
  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name="Configuaration_id")
  private Configuration configuration;
  
  
  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name="CATALOG_ID")
  private Catalog catalog;
  
  @OneToMany(cascade={javax.persistence.CascadeType.ALL})
  @JoinTable(name="OOMS_USER_CUSTOMERS", joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")}, inverseJoinColumns={@JoinColumn(name="CUSTOMER_ID", referencedColumnName="ID")})
  private List<Customer> customers;
  
  @OneToMany(cascade={javax.persistence.CascadeType.ALL})
  @JoinTable(name="OOMS_USER_SUPPLIERS", joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")}, inverseJoinColumns={@JoinColumn(name="SUPPLIER_ID", referencedColumnName="ID")})
  private List<Supplier> suppliers;
  
  @OneToMany(cascade={javax.persistence.CascadeType.ALL})
  private List<Notification> notifications;
  
  @OneToMany(cascade={javax.persistence.CascadeType.ALL})
  private List<Tax> taxes;
  
 
  
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
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) { this.password = password; }
  
  public List<Customer> getCustomers() {
    return customers;
  }
  
  public void setCustomers(List<Customer> customers) { this.customers = customers; }

public Date getDateOfRegistration() {
	return dateOfRegistration;
}

public void setDateOfRegistration(Date dateOfRegistration) {
	this.dateOfRegistration = dateOfRegistration;
}

public Address getAddress() {
	return address;
}

public void setAddress(Address address) {
	this.address = address;
}

public List<Supplier> getSuppliers() {
	return suppliers;
}

public void setSuppliers(List<Supplier> suppliers) {
	this.suppliers = suppliers;
}

public Catalog getCatalog() {
	return catalog;
}

public void setCatalog(Catalog catalog) {
	this.catalog = catalog;
}

public int getStatus() {
	return status;
}

public void setStatus(int status) {
	this.status = status;
}

public Configuration getConfiguration() {
	return configuration;
}

public void setConfiguration(Configuration configuration) {
	this.configuration = configuration;
}

public List<Notification> getNotifications() {
	return notifications;
}

public void setNotifications(List<Notification> notifications) {
	this.notifications = notifications;
}

public List<Tax> getTaxes() {
	return taxes;
}

public void setTaxes(List<Tax> taxes) {
	this.taxes = taxes;
}
  

}
