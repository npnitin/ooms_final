package com.example.exceptions;

public class OrderTransitionNotSupported extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderTransitionNotSupported(String message) {
		super(message);
	}
}
