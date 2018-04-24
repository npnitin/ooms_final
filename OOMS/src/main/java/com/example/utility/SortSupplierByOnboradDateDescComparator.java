package com.example.utility;

import java.util.Comparator;

import com.example.bo.Customer;
import com.example.bo.Supplier;

public class SortSupplierByOnboradDateDescComparator implements Comparator<Supplier> {


	@Override
	public int compare(Supplier s1, Supplier s2) {
		if(s2.getOnboardDate().before(s1.getOnboardDate())) {
			return -1;
		}
		else if(s2.getOnboardDate().after(s1.getOnboardDate())) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
