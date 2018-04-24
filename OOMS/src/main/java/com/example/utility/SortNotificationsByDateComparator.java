package com.example.utility;

import java.util.Comparator;

import com.example.bo.Notification;

public class SortNotificationsByDateComparator implements Comparator<Notification> {

	@Override
	public int compare(Notification o1, Notification o2) {
		if(o1.getDate().after(o2.getDate())) {
			return -1;
		}
		else if(o1.getDate().before(o2.getDate())) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
