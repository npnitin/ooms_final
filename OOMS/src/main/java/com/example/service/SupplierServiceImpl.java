package com.example.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bo.Notification;
import com.example.bo.OrderItem;
import com.example.bo.PurchaseOrder;
import com.example.bo.ResponseBo;
import com.example.bo.Supplier;
import com.example.bo.User;
import com.example.config.BasicConfig;
import com.example.dao.PurchaseOrderRepository;
import com.example.dao.SupplierRepository;
import com.example.dao.UserRepository;
import com.example.dto.OrderItemDto;
import com.example.dto.PurchaseOrderDto;
import com.example.mailutil.Mail;
import com.example.mailutil.MailService;
import com.example.utility.OOMSConstants;
import com.example.utility.PurchaseOrderToPurchaseOrderDtoConvertor;
import com.example.utility.SortPurchaseOrderByOrderDateDesc;

@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {
	
	
	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;
	
	
	@Autowired
	SupplierRepository supplierRepository;
	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BasicConfig configProperties;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	PurchaseOrderToPurchaseOrderDtoConvertor purchaseOrderToPurchaseOrderDtoConvertor;

	@Override
	public List<PurchaseOrder> getAllPurchaseOrdersByUserId(String userId) {
		
		int uid = Integer.parseInt(userId);
		User user = userRepository.findOne(uid);
		List<Supplier> suppliers = user.getSuppliers();
		List<PurchaseOrder> purchaseOrders = new ArrayList<>();
		for (Supplier supplier : suppliers) {
			purchaseOrders.addAll(supplier.getOrders());
		}
		Collections.sort(purchaseOrders, new SortPurchaseOrderByOrderDateDesc());
		return purchaseOrders;
	}

	@Override
	public ResponseBo addPurchaseOrder(String userId,String supplierId, PurchaseOrder purchaseOrder) {
		int sid = Integer.parseInt(supplierId);
		purchaseOrder.setOrderDate(new Date());
		purchaseOrder.setStatus("NEW");
		
		
		PurchaseOrder savedPurchaseOrder = purchaseOrderRepository.save(purchaseOrder);
		
		Supplier supplier = supplierRepository.findOne(sid);
		savedPurchaseOrder.setSupplier(supplier);
		supplier.getOrders().add(savedPurchaseOrder);
		supplierRepository.save(supplier);
		
		int uid = Integer.parseInt(userId);
		User user = userRepository.findOne(uid);
		Notification notification = new Notification();
		notification.setCategory("Supplier PurchaseOrder");
		notification.setDate(new Date());
		notification.setStatusRead(false);
		notification.setHeader("Purchase order of Rs."+savedPurchaseOrder.getTotalCost());
		notification.setMessage("Purchase order for supplier "+savedPurchaseOrder.getSupplier().getName()+" is added.for more details check purchase order id #"+savedPurchaseOrder.getId());
		user.getNotifications().add(notification);
		userRepository.save(user);
		
		ResponseBo response = new ResponseBo();
		response.setErrorCode("200");
		response.setMessage("PurchaseOrder Added succesfully");
		
		//Send mail to supplier
		PurchaseOrderDto purchaseOrderDto = purchaseOrderToPurchaseOrderDtoConvertor.convert(savedPurchaseOrder);
		Mail mail = createPurchaseOrderMail(user,supplier,purchaseOrderDto);
		mailService.sendEmail(mail, OOMSConstants.SEND_PURCHASE_ORDER_TO_SUPPLIER);
		return response;
	}

	private Mail createPurchaseOrderMail(User user,Supplier supplier, PurchaseOrderDto purchaseOrderDto) {
		Mail mail = new Mail();
        mail.setMailFrom(configProperties.getMail());
        mail.setMailTo(supplier.getEmail());
        mail.setMailSubject(OOMSConstants.SEND_PURCHASE_ORDER_TO_SUPPLIER);
        Map < String, Object > model = new HashMap < String, Object > ();
        
       List<OrderItemDto> items = purchaseOrderDto.getItems();
       model.put("id", purchaseOrderDto.getId());
       model.put("Items", items);
       model.put("OrderDate", purchaseOrderDto.getOrderDate().toString());
       model.put("name",supplier.getName());
       model.put("total_cost", purchaseOrderDto.getTotalCost());
       model.put("signature",configProperties.getSignature());
       model.put("address",configProperties.getAddress());
       model.put("user",user);
       mail.setModel(model);
       return mail;
	}

}
