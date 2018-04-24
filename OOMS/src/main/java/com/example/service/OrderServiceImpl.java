package com.example.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bo.Notification;
import com.example.bo.Order;
import com.example.bo.OrderItem;
import com.example.bo.ProductItem;
import com.example.bo.ResponseBo;
import com.example.bo.User;
import com.example.dao.OrderRepository;
import com.example.dao.ProductItemRepository;
import com.example.dao.UserRepository;
import com.example.dto.OrderDto;
import com.example.dto.OrderItemDto;
import com.example.utility.OOMSConstants;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderReposotory;
	
	@Autowired
	ProductItemRepository productItemRepository;
	
	@Autowired
	UserRepository userRepository;
	

	@Override
	public Order getOrderDetailsById(int id) {
		
		return orderReposotory.getOne(id);
	}

	@Override
	public OrderDto convertoToOrderDto(Order order) {
		OrderDto orderDto = new OrderDto();
		orderDto.setId(order.getId());
		orderDto.setAdvanceAmount(order.getAdvanceAmount());
		List<OrderItem> items = order.getItems();
		List<OrderItemDto> itemsToReturn = new ArrayList<>(items.size());
		for (OrderItem orderItem : items) {
			ProductItem productItem = productItemRepository.findOne(orderItem.getItemId());
			OrderItemDto newOrderItemDto = new OrderItemDto();
			newOrderItemDto.setCount(orderItem.getCount());
			newOrderItemDto.setItem(productItem);
			itemsToReturn.add(newOrderItemDto);
		}
		orderDto.setItems(itemsToReturn);
		orderDto.setStatus(order.getStatus());
		orderDto.setTotalCost(order.getTotalCost());
		orderDto.setRemainingbalance(order.getRemainingbalance());
		orderDto.setCustomer(order.getCustomer());
		orderDto.setTax(order.getTax());
		SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateText = df2.format(order.getDateOfOrder());
        orderDto.setDateOfOrder(dateText);
        return orderDto;
	}

	@Override
	public ResponseBo changeOrderStatusToReadyToDispacth(String orderId) {
		int oid = Integer.parseInt(orderId);
		Order order = orderReposotory.findOne(oid);
		ResponseBo response =  new ResponseBo();
		if(order.getStatus().equalsIgnoreCase(OOMSConstants.ORDER_READY_TO_DISPATCH)) {
			response.setErrorCode("400");
			response.setMessage("Order "+order.getId()+" is already in "+OOMSConstants.ORDER_READY_TO_DISPATCH+" status");
			return response;
		}
		order.setStatus("READY TO DISPATCH");
		orderReposotory.save(order);
		
		response.setErrorCode("200");
		response.setMessage("Order status changed succesfully!!");
		return response;
	}

	@Override
	public ResponseBo deleteOrder(int orderId) {
		ResponseBo response = new ResponseBo();
		orderReposotory.delete(orderId);
		response.setErrorCode("200");
		response.setMessage("Order deleted succesfully");
		return response;
	}

	
	@Override
	public ResponseBo changeOrderStatusToClose(String userId,String orderId) {
		int uid = Integer.parseInt(userId);
		int oid = Integer.parseInt(orderId);
		Order order = orderReposotory.findOne(oid);
		ResponseBo response =  new ResponseBo();
		if(order.getStatus().equalsIgnoreCase(OOMSConstants.ORDER_CLOSED)) {
			response.setErrorCode("400");
			response.setMessage("Order "+order.getId()+" is already in "+OOMSConstants.ORDER_CLOSED+" status");
			return response;
		}
		order.setStatus("CLOSED");
		order.setAdvanceAmount(order.getAdvanceAmount()+order.getRemainingbalance());
		order.setRemainingbalance(00);
		orderReposotory.save(order);
		
		response.setErrorCode("200");
		response.setMessage("Order status changed succesfully!!");
		
		Notification notification = new Notification();
		notification.setCategory("Customer Order Closed");
		notification.setDate(new Date());
		notification.setMessage("Status for order id:"+order.getId()+" is changed to "+OOMSConstants.ORDER_CLOSED);
		User user  = userRepository.findOne(uid);
		user.getNotifications().add(notification);
		userRepository.save(user);
		return response;
	}
	
	
}
