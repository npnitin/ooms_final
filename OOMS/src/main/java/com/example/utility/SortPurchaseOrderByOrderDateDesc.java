package com.example.utility;

import java.util.Comparator;

import com.example.bo.PurchaseOrder;

public class SortPurchaseOrderByOrderDateDesc implements Comparator<PurchaseOrder> {

	@Override
	public int compare(PurchaseOrder o1, PurchaseOrder o2) {
		if(o2.getOrderDate().before(o1.getOrderDate())) {
			return -1;
		}
		else if(o2.getOrderDate().after(o1.getOrderDate())) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
