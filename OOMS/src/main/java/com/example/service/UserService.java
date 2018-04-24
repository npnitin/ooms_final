package com.example.service;

import java.util.List;

import com.example.bo.Catalog;
import com.example.bo.Configuration;
import com.example.bo.Credential;
import com.example.bo.Customer;
import com.example.bo.Notification;
import com.example.bo.Order;
import com.example.bo.ProductItem;
import com.example.bo.ResponseBo;
import com.example.bo.Supplier;
import com.example.dto.CustomerDto;
import com.example.dto.HomeDto;
import com.example.dto.NotificationDto;
import com.example.dto.PurchaseOrderDto;
import com.example.dto.SupplierDto;
import com.example.dto.UserDto;

public interface UserService {

	ResponseBo doLogin(Credential credential);
	String authenticateUser(Credential credential);
	String doLogout(Credential credential);
	List<Order> getAllOrdersByUserId(String userId);
	Catalog getCatalogByUserId(String userId);
	ResponseBo addItemToCatalog(String userId,ProductItem item);
	ResponseBo deleteItemFromCatalog(String userId,String itemId);
	ResponseBo updateItemFromCatalog(String userId,String itemId,ProductItem productItem);
	List<Customer> getCustomersByUserId(String userId);
	ResponseBo addCustomer(String userId,Customer customer);
	List<Order> getOrdersByUserId(String userId);
	ResponseBo addOrder(String userId,String customerId,Order order);
	ResponseBo addSupplier(String userId,Supplier supplier);
	List<SupplierDto> getSuppliersByUserId(String userId);
	Customer getCustomerByIdAndUserId(String userId,String customerId);
	CustomerDto convertCustomerToCustomerDto(Customer customer);
	SupplierDto converSuppliertToSupplierDto(Supplier supplier);
	ResponseBo deleteCustomerByUserIdAndId(String userId,String customerId);
	HomeDto getHomeDto(String userId);
	ResponseBo validateEmailAndPhone(String email,String phone);
	Configuration getConfigurationByUserId(String userId);
	ResponseBo addConfigurationByUserId(String userId,Configuration configuration);
	ResponseBo updateCustomer(String userId,String customerId,Customer customer);
	ResponseBo deleteOrder(String userId,String customerId,String orderId);
	ResponseBo deleteSupplier(String userId,String supplierId);
	ResponseBo updateSupplier(String userId,String supplierId,Supplier newSupplierDetails);
	SupplierDto getSupplierDetailsById(String supplierId);
	ResponseBo deletePurchaseOrderById(String supplierId,String POid);
	PurchaseOrderDto getPurchaseOrderById(String supplierId,String poid);
	List<NotificationDto> getAllNotificationsByUserId(String userId);
	UserDto getProfileByUserId(String userId);
}
