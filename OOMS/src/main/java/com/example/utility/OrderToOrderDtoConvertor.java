package com.example.utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.bo.Order;
import com.example.bo.OrderItem;
import com.example.bo.ProductItem;
import com.example.dao.ProductItemRepository;
import com.example.dto.OrderDto;
import com.example.dto.OrderItemDto;

public class OrderToOrderDtoConvertor {
	
	@Autowired
	static ProductItemRepository productItemRepository;
	
	public static OrderDto convert(Order order) {
		OrderDto orderDto = new OrderDto();
		orderDto.setId(order.getId());
		orderDto.setAdvanceAmount(order.getAdvanceAmount());
		List<OrderItem> items = order.getItems();
		List<OrderItemDto> itemsToReturn = new ArrayList<>(items.size());
		for (OrderItem orderItem : items) {
			ProductItem productItem = productItemRepository.getOne(orderItem.getItemId());
			OrderItemDto newOrderItemDto = new OrderItemDto();
			newOrderItemDto.setCount(orderItem.getCount());
			newOrderItemDto.setItem(productItem);
			itemsToReturn.add(newOrderItemDto);
		}
		orderDto.setItems(itemsToReturn);
		orderDto.setTotalCost(order.getTotalCost());
		orderDto.setRemainingbalance(order.getRemainingbalance());
		orderDto.setCustomer(order.getCustomer());
		orderDto.setTax(order.getTax());
		SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        String dateText = df2.format(order.getDateOfOrder());
        orderDto.setDateOfOrder(dateText);
        return orderDto;
	}

}
