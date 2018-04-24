package com.example.utility;

import java.util.Comparator;

import com.example.bo.Customer;

public class SortCustomerByOnboradDateAscComparator implements Comparator<Customer> {


	@Override
	public int compare(Customer o1, Customer o2) {
		if(o2.getOnboardDate().before(o1.getOnboardDate())) {
			return -1;
		}
		else if(o2.getOnboardDate().after(o1.getOnboardDate())) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
