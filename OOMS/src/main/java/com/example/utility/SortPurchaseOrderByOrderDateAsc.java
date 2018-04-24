package com.example.utility;

import java.util.Comparator;

import com.example.bo.PurchaseOrder;

public class SortPurchaseOrderByOrderDateAsc implements Comparator<PurchaseOrder> {

	@Override
	public int compare(PurchaseOrder o1, PurchaseOrder o2) {
		if(o1.getOrderDate().before(o2.getOrderDate())) {
			return -1;
		}
		else if(o1.getOrderDate().after(o2.getOrderDate())) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
