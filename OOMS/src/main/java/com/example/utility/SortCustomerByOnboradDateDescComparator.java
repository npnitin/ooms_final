package com.example.utility;

import java.util.Comparator;

import com.example.bo.Customer;

public class SortCustomerByOnboradDateDescComparator implements Comparator<Customer> {


	@Override
	public int compare(Customer o1, Customer o2) {
		if(o1.getOnboardDate().before(o2.getOnboardDate())) {
			return -1;
		}
		else if(o1.getOnboardDate().after(o2.getOnboardDate())) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
