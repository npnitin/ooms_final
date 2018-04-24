package com.example.utility;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bo.PurchaseOrder;
import com.example.bo.Supplier;
import com.example.dto.PurchaseOrderDto;
import com.example.dto.SupplierDto;

@Component("supplierToSupplierDtoConvertor")
public class SupplierToSupplierDtoConvertor {
	
	@Autowired
	PurchaseOrderToPurchaseOrderDtoConvertor purchaseOrderToPurchaseOrderDtoConvertor;
	
	public SupplierDto convert(Supplier supplier) {
		SupplierDto supplierDto = new SupplierDto();
		
		
		supplierDto.setId(supplier.getId());
		supplierDto.setName(supplier.getName());
		supplierDto.setEmail(supplier.getEmail());
		supplierDto.setPhone(supplier.getPhone());
		supplierDto.setAddress(supplier.getAddress());
		SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
		if(null != supplier.getOnboardDate()) {
			 String dateText = df2.format(supplier.getOnboardDate());
		        supplierDto.setOnboardDate(dateText);
		}
       
		Set<PurchaseOrderDto> purchaseOrderDtos = new HashSet<>();
		for(PurchaseOrder purchaseOrder : supplier.getOrders()) {
			purchaseOrderDtos.add(purchaseOrderToPurchaseOrderDtoConvertor.convert(purchaseOrder));
		}
		supplierDto.setOrders(purchaseOrderDtos);
		return supplierDto;
	}
	
}
