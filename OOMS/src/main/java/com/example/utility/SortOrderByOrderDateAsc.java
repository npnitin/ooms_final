package com.example.utility;

import java.util.Comparator;

import com.example.bo.Order;

public class SortOrderByOrderDateAsc implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {
		if(o1.getDateOfOrder().before(o2.getDateOfOrder())) {
			return -1;
		}
		else if(o1.getDateOfOrder().after(o2.getDateOfOrder())) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
