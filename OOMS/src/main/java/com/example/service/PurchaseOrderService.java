package com.example.service;

import com.example.bo.ResponseBo;
import com.example.exceptions.OrderTransitionNotSupported;

public interface PurchaseOrderService {
	
	public ResponseBo changeOrderStatus(String userId,String orderId,String status);

}
