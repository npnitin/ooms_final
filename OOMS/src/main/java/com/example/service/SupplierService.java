package com.example.service;

import java.util.List;
import java.util.Set;

import com.example.bo.PurchaseOrder;
import com.example.bo.ResponseBo;

public interface SupplierService {
	
	public List<PurchaseOrder> getAllPurchaseOrdersByUserId(String userId);
	public ResponseBo addPurchaseOrder(String userId,String supplierId,PurchaseOrder purchaseOrder);
	
	

}
