package com.example.utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bo.OrderItem;
import com.example.bo.ProductItem;
import com.example.bo.PurchaseOrder;
import com.example.dao.ProductItemRepository;
import com.example.dto.OrderItemDto;
import com.example.dto.PurchaseOrderDto;

@Component("purchaseOrderToPurchaseOrderDtoConvertor")
public class PurchaseOrderToPurchaseOrderDtoConvertor {
	
	@Autowired
	ProductItemRepository productItemRepository;
	
	public  PurchaseOrderDto convert(PurchaseOrder purchaseOrder) {
		PurchaseOrderDto purchaseOrderDto = new PurchaseOrderDto();
		purchaseOrderDto.setId(purchaseOrder.getId());
		List<OrderItemDto> orderItemDtos = new ArrayList<>();
		for (OrderItem orderItem :  purchaseOrder.getItems()) {
			orderItemDtos.add(convertOrderitemToOrderItemDto(orderItem));
		}
		purchaseOrderDto.setItems(orderItemDtos);
		purchaseOrderDto.setStatus(purchaseOrder.getStatus());
		purchaseOrderDto.setSupplier(purchaseOrder.getSupplier());
		purchaseOrderDto.setTotalCost(purchaseOrder.getTotalCost());
		SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        String dateText = df2.format(purchaseOrder.getOrderDate());
        purchaseOrderDto.setOrderDate(dateText);
        return purchaseOrderDto;
	}

	
	private OrderItemDto convertOrderitemToOrderItemDto(OrderItem orderItem) {
		ProductItem productItem = productItemRepository.findOne(orderItem.getItemId());
		OrderItemDto newOrderItemDto = new OrderItemDto();
		newOrderItemDto.setCount(orderItem.getCount());
		newOrderItemDto.setItem(productItem);
		return newOrderItemDto;
	}
}
