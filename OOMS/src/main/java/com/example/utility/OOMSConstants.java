package com.example.utility;

public class OOMSConstants {
	
	private OOMSConstants() {
		
	}
	
	public static final String ORDER_NEW="NEW";
	public static final String ORDER_CLOSED="CLOSED";
	public static final String ORDER_SUBMITTED="SUBMITTED";
	public static final String ORDER_CANCELED="CANCELD";
	public static final String ORDER_NOTIFIED="NOTIFIED";
	public static final String ORDER_READY_TO_DISPATCH="READY TO DISPATCH";
	
	/*
	 * Email template constants
	 */
	public static final String EMAIL_ACC_ACTIVATION_HEADING= "OOMS Acount Activation mail";
	public static final String URL_PREFIX= "http://";
	public static final String LOGIN_PAGE= "login.html";
	public static final String HOME_PAGE= "index.html";
	public static final String ACC_ACTIVATION_URL = "/user/activateAccount?userId=";
	public static final String SEND_ACC_ACTIVATION_STRING = "activate";
	public static final String SEND_ORDER_TO_CUSTOMER = "send_order";
	public static final String SEND_PURCHASE_ORDER_TO_SUPPLIER = "send_purchaseorder";
	
	
	public static final String CUSTOMER_ORDDER_HEADING = "OOMS Customer Order";
}
