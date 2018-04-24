package com.example.dto;

import com.example.bo.ProductItem;

public class OrderItemDto {
	
	  private int id;
	  
	  private ProductItem item;
	  
	  private int count;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProductItem getItem() {
		return item;
	}

	public void setItem(ProductItem item) {
		this.item = item;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	  
	  

}
