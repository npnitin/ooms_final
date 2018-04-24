package com.example.bo;

import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="OOMS_CATALOG")
public class Catalog
{
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="ID")
  private int id;
  
  @Column(name="catalog_name")
  private String name;
  
  @Column(name="dateOfCreation")
  private Date dateOfCreation;
  
  @OneToMany(cascade={javax.persistence.CascadeType.ALL})
  @JoinTable(name="OOMS_CATALOG_ITEMS", joinColumns={@javax.persistence.JoinColumn(name="CATALOG_ID", referencedColumnName="ID")}, inverseJoinColumns={@javax.persistence.JoinColumn(name="ITEM_ID", referencedColumnName="ID")})
  private Set<ProductItem> items;
  
  @Column(name="status")
  private STATUS status;
  
  public Catalog() {}
  
  public int getId()
  {
    return id;
  }
  
  public void setId(int id) { this.id = id; }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) { this.name = name; }
  
  public Date getDateOfCreation() {
    return dateOfCreation;
  }
  
  public void setDateOfCreation(Date dateOfCreation) { this.dateOfCreation = dateOfCreation; }
  
  public Set<ProductItem> getItems() {
    return items;
  }
  
  public void setItems(Set<ProductItem> items) { this.items = items; }
  
  public STATUS getStatus() {
    return status;
  }
  
  public void setStatus(STATUS status) { this.status = status; }
}
