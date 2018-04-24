package com.example.utility;

import java.util.Comparator;

import com.example.bo.Customer;
import com.example.bo.Supplier;

public class SortSupplierByOnboradDateAscComparator implements Comparator<Supplier> {


	@Override
	public int compare(Supplier s1, Supplier s2) {
		if(s1.getOnboardDate().before(s2.getOnboardDate())) {
			return -1;
		}
		else if(s1.getOnboardDate().after(s2.getOnboardDate())) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
