package com.example.service;

import com.example.bo.Order;
import com.example.bo.ResponseBo;
import com.example.dto.OrderDto;

public interface OrderService {

	Order getOrderDetailsById(int id);
	OrderDto convertoToOrderDto(Order order);
	ResponseBo changeOrderStatusToReadyToDispacth(String orderid);
	ResponseBo deleteOrder(int orderId);
	ResponseBo changeOrderStatusToClose(String userId,String orderId);
}
