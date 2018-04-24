package com.example.utility;

import java.util.Comparator;

import com.example.bo.Order;

public class SortOrderByOrderDateDesc implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {
		if(o2.getDateOfOrder().before(o1.getDateOfOrder())) {
			return -1;
		}
		else if(o2.getDateOfOrder().after(o1.getDateOfOrder())) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
