package com.example.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bo.Notification;
import com.example.bo.OrderItem;
import com.example.bo.ProductItem;
import com.example.bo.PurchaseOrder;
import com.example.bo.ResponseBo;
import com.example.bo.User;
import com.example.dao.ProductItemRepository;
import com.example.dao.PurchaseOrderRepository;
import com.example.dao.UserRepository;
import com.example.exceptions.OrderTransitionNotSupported;
import com.example.utility.OOMSConstants;

@Service("purchaseOrderService")
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;
	
	
	@Autowired
	ProductItemRepository productItemRepository;
	
	
	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public ResponseBo changeOrderStatus(String userId,String orderId, String status) {
		int uid = Integer.parseInt(userId);
		int oid = Integer.parseInt(orderId);
		ResponseBo response = new ResponseBo();
		PurchaseOrder purchaseOrder = purchaseOrderRepository.findOne(oid);
		Notification notification = new Notification();
		notification.setCategory("PurchaseOrder Status");
		notification.setDate(new Date());
		notification.setHeader("Purchase Order status changed for pruchaseOrderID:"+purchaseOrder.getId());
		switch(purchaseOrder.getStatus()) {
		case OOMSConstants.ORDER_NEW:
			//If order status is new we can only make it to submitted status OR cancel
			if(status.equalsIgnoreCase("SUBMIT")) {
				notification.setMessage("Purchase order status changed from "+purchaseOrder.getStatus()+" to "+OOMSConstants.ORDER_SUBMITTED);
				purchaseOrder.setStatus(OOMSConstants.ORDER_SUBMITTED);
				response.setErrorCode("200");
				response.setMessage("Success");
			}
			else if(status.equalsIgnoreCase("CANCEL")) {
				notification.setMessage("Purchase order status changed from "+purchaseOrder.getStatus()+" to "+OOMSConstants.ORDER_CANCELED);
				purchaseOrder.setStatus(OOMSConstants.ORDER_CANCELED);
				response.setErrorCode("200");
				response.setMessage("Success");
			}
			else{
				response.setErrorCode("500");
				response.setMessage("Can not perform any this operation on "+purchaseOrder.getStatus()+" order.");
			}
				
			break;
		case OOMSConstants.ORDER_SUBMITTED:
			//If order is in submitted we can make it close,cancel,resend notificaton
			if(status.equalsIgnoreCase("CANCEL")) {
				notification.setMessage("Purchase order status changed from "+purchaseOrder.getStatus()+" to "+OOMSConstants.ORDER_CANCELED);
				purchaseOrder.setStatus(OOMSConstants.ORDER_CANCELED);
				response.setErrorCode("200");
				response.setMessage("Success");
			}
			else if(status.equalsIgnoreCase("NOTIFY")) {
				notification.setMessage("Purchase order status changed from "+purchaseOrder.getStatus()+" to "+OOMSConstants.ORDER_NOTIFIED);
				purchaseOrder.setStatus(OOMSConstants.ORDER_NOTIFIED);
				response.setErrorCode("200");
				response.setMessage("Success");
			}
			else if(status.equalsIgnoreCase("CLOSE")) {
				//product item count needs to be incremented as we receive the order
				notification.setMessage("Purchase order status changed from "+purchaseOrder.getStatus()+" to "+OOMSConstants.ORDER_CLOSED);
				purchaseOrder.setStatus(OOMSConstants.ORDER_CLOSED);
				response.setErrorCode("200");
				response.setMessage("Success");
				for(OrderItem item : purchaseOrder.getItems()) {
					ProductItem productItem = productItemRepository.findOne(item.getItemId());
					productItem.setQuantity(productItem.getQuantity()+item.getCount());
					productItemRepository.save(productItem);
				}
			}
			else {
				response.setErrorCode("500");
				response.setMessage("Can not perform any this operation on "+purchaseOrder.getStatus()+" order.");
			}
				
			break;
		case OOMSConstants.ORDER_CLOSED:
			response.setErrorCode("500");
			response.setMessage("Can not perform any this operation on "+purchaseOrder.getStatus()+" order.");
			break;
			
		case OOMSConstants.ORDER_CANCELED:
			response.setErrorCode("500");
			response.setMessage("Can not perform any this operation on "+purchaseOrder.getStatus()+" order.");
			break;
			
		case OOMSConstants.ORDER_NOTIFIED:
			if(status.equalsIgnoreCase("CLOSE")) {
				notification.setMessage("Purchase order status changed from "+purchaseOrder.getStatus()+" to "+OOMSConstants.ORDER_CLOSED);
				purchaseOrder.setStatus(OOMSConstants.ORDER_CLOSED);
				response.setErrorCode("200");
				response.setMessage("Success");
			}
			else if(status.equalsIgnoreCase("CANCEL")) {
				notification.setMessage("Purchase order status changed from "+purchaseOrder.getStatus()+" to "+OOMSConstants.ORDER_CANCELED);
				purchaseOrder.setStatus(OOMSConstants.ORDER_CANCELED);
				response.setErrorCode("200");
				response.setMessage("Success");
			}
			else {
				response.setErrorCode("500");
				response.setMessage("Can not perform any this operation on "+purchaseOrder.getStatus()+" order.");
			}
			
		}
		User user = userRepository.findOne(uid);
		user.getNotifications().add(notification);
		userRepository.save(user);
		purchaseOrderRepository.save(purchaseOrder);
		return response;
	}

}
