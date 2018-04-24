package com.example.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bo.Auth;
import com.example.bo.Catalog;
import com.example.bo.Configuration;
import com.example.bo.Credential;
import com.example.bo.Customer;
import com.example.bo.Notification;
import com.example.bo.Order;
import com.example.bo.OrderItem;
import com.example.bo.ProductItem;
import com.example.bo.PurchaseOrder;
import com.example.bo.ResponseBo;
import com.example.bo.Supplier;
import com.example.bo.Tax;
import com.example.bo.User;
import com.example.config.BasicConfig;
import com.example.dao.AuthRepository;
import com.example.dao.CustomerRepository;
import com.example.dao.OrderRepository;
import com.example.dao.ProductItemRepository;
import com.example.dao.SupplierRepository;
import com.example.dao.TaxRepository;
import com.example.dao.UserRepository;
import com.example.dto.CustomerDto;
import com.example.dto.HomeDto;
import com.example.dto.NotificationDto;
import com.example.dto.OrderDto;
import com.example.dto.OrderItemDto;
import com.example.dto.PurchaseOrderDto;
import com.example.dto.SupplierDto;
import com.example.dto.UserDto;
import com.example.mailutil.Mail;
import com.example.mailutil.MailService;
import com.example.utility.NotificationToNotificatioDtoConvertor;
import com.example.utility.OOMSConstants;
import com.example.utility.PurchaseOrderToPurchaseOrderDtoConvertor;
import com.example.utility.SortNotificationsByDateComparator;
import com.example.utility.SortOrderByOrderDateDesc;
import com.example.utility.SupplierToSupplierDtoConvertor;
import com.example.utility.UserToUserDtoConvertor;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	AuthRepository authRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	SupplierRepository supplierRepository;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductItemRepository productItemRepository;
	
	@Autowired
	TaxRepository taxRepository;
	
	@Autowired
	PurchaseOrderToPurchaseOrderDtoConvertor purchaseOrderToPurchaseOrderDtoConvertor;
	
	@Autowired
	SupplierToSupplierDtoConvertor supplierToSupplierDtoConvertor;
	
	@Autowired
	NotificationToNotificatioDtoConvertor notificationToNotificatioDtoConvertor;
	
	@Autowired
	UserToUserDtoConvertor userToUserDtoConvertor;
	
	@Autowired
	BasicConfig configProperties;
	
	@Autowired
	MailService mailService;

	@Override
	public ResponseBo doLogin(Credential credential) {
		User user  = userRepository.getUser(credential.getEmail(), credential.getPassword());
		ResponseBo response = new ResponseBo();
		if(user != null) {
			if(user.getStatus() == 1) {
				Auth auth = new Auth();
				auth.setEmail(credential.getEmail());
				auth.setTimeOfTokenIssued(new Date());
				String token = UUID.randomUUID().toString();
				auth.setToken(token);
				auth.setTokenValidTime(100000);
				authRepository.save(auth);
				response.setErrorCode("200");
				response.setMessage(token);
				response.setId(user.getId());
				return response;
			}
			else {
				response.setErrorCode("300");
				response.setMessage("User not activated");
				response.setId(-1);
				return response;
			}
			
		}
		else{
			response.setErrorCode("500");
			response.setMessage("User not found");
			response.setId(-1);
			return response;
		}
	}

	@Override
	public String authenticateUser(Credential credential) {
		Auth auth = authRepository.authenticateUser(credential.getEmail(), credential.getPassword());
		if(auth == null) {
			return "failed";
		}
		else {
			return "success";
		}
	}

	@Override
	public String doLogout(Credential credential) {
		Auth auth = authRepository.authenticateUser(credential.getEmail(), credential.getPassword());
		if(auth == null) {
			return "failed";
		}
		else {
			authRepository.delete(auth);
			return "success";
		}
	}

	@Override
	public List<Order> getAllOrdersByUserId(String userId) {
		int id = Integer.parseInt(userId);
		List<Customer> customers = userRepository.getOne(id).getCustomers();
		List<Order> orders = new ArrayList<>();
		Iterator itr = customers.iterator();
		while(itr.hasNext()) {
			Customer customer  =  (Customer) itr.next();
			orders.addAll(customer.getOrders());
		}
		return orders;
	}

	@Override
	public Catalog getCatalogByUserId(String userId) {
		int id = Integer.parseInt(userId);
		return userRepository.getOne(id).getCatalog();
		
	}

	@Override
	public ResponseBo addItemToCatalog(String userId, ProductItem item) {
		int id = Integer.parseInt(userId);
		User  user = userRepository.findOne(id);
		user.getCatalog().getItems().add(item);
		userRepository.save(user);
		ResponseBo response = new ResponseBo();
		response.setErrorCode("200");
		response.setMessage("success");
		return response;
	}

	@Override
	public List<Customer> getCustomersByUserId(String userId) {
		int id = Integer.parseInt(userId);
		return userRepository.getOne(id).getCustomers();
	}

	@Override
	public ResponseBo addCustomer(String userId,Customer customer) {
		int id = Integer.parseInt(userId);
		customer.setOnboardDate(new Date());
		ResponseBo response = new ResponseBo();
		List<Customer> customers  = customerRepository.getCustomerByEmailAndMobile(customer.getEmail(), customer.getMobile());
		if(customers.size() != 0) {
			response.setMessage(Integer.toString(customers.get(0).getId()));
		}
		else {
			Customer savedCustomer = customerRepository.save(customer);
			User  user = userRepository.findOne(id);
			user.getCustomers().add(savedCustomer);
			userRepository.save(user);
			response.setMessage(Integer.toString(savedCustomer.getId()));
			
		}
		response.setErrorCode("200");
		
		return response;
	}

	@Override
	public List<Order> getOrdersByUserId(String userId) {
		int id = Integer.parseInt(userId);
		List<Customer> customers = userRepository.getOne(id).getCustomers();
		List<Order> orders = new ArrayList<>();
		for (Customer customer : customers) {
			orders.addAll(customer.getOrders());
		}
		Collections.sort(orders, new SortOrderByOrderDateDesc());
		return orders;
	}

	@Override
	public ResponseBo addOrder(String userId,String customerId,Order order) {
		int uid = Integer.parseInt(userId);
		int cid = Integer.parseInt(customerId);
		ResponseBo response = new ResponseBo();
		//Order status=NEW,READY TO DISPATCH,COMPLETED
		order.setDateOfOrder(new Date());
		order.setStatus("NEW");
		
		 User user = userRepository.findOne(uid);
		 int threshHoldCount = 0;
		//Reduce item count
		for(OrderItem orderItem :order.getItems()) {
			int itemId = orderItem.getItemId();
			int count = orderItem.getCount();
			ProductItem productItem = productItemRepository.findOne(itemId);
			if(productItem.getQuantity() >= count) {
				productItem.setQuantity(productItem.getQuantity()-count);
				//add notification if item count is less than threshhold value
				if(threshHoldCount >= productItem.getQuantity()) {
					Notification notification = new Notification();
					notification.setCategory("ProductItem OutOfStock");
					notification.setDate(new Date());
					notification.setHeader(productItem.getName()+" is going to be exhausted");
					notification.setStatusRead(false);
					notification.setMessage(productItem.getName()+" "+productItem.getDescription()+" is going to be exhausted.Only "+productItem.getQuantity()+" items are remaining.");
					user.getNotifications().add(notification);
					userRepository.save(user);
				}
			}
			else {
				response.setErrorCode("500");
				response.setMessage(productItem.getQuantity()+" quantity is available for product"+productItem.getName());
				return response;
			}
			productItemRepository.save(productItem);
		}
		Tax tax = taxRepository.findOne(order.getTax().getId());
		order.setTax(tax);
		Order savedOrder  = orderRepository.save(order);
		
		int id = Integer.parseInt(customerId);
		Customer customer = customerRepository.findOne(id);
		savedOrder.setCustomer(customer);
		customer.getOrders().add(savedOrder);
		customerRepository.save(customer);
		response.setErrorCode("200");
		response.setMessage("success");
		
		//add entry in notifications for order
		Notification notification = new Notification();
		notification.setCategory("Customer Order");
		notification.setDate(new Date());
		notification.setHeader("Order of Rs."+savedOrder.getTotalCost());
		notification.setStatusRead(false);
		notification.setMessage("Advance collected is: RS."+savedOrder.getAdvanceAmount()+".Balance remaining is Rs."+savedOrder.getRemainingbalance()+".Customer is:"+savedOrder.getCustomer().getName()+".For more details please check order id #"+savedOrder.getId());
		user.getNotifications().add(notification);
		userRepository.save(user);
		
		//send mail to customer
		OrderDto orderDto = orderService.convertoToOrderDto(savedOrder);
		Mail mail = createOrderMail(orderDto,customer);
		mailService.sendEmail(mail, OOMSConstants.SEND_ORDER_TO_CUSTOMER);
		return response;
		
	}

	private Mail createOrderMail(OrderDto order, Customer customer) {
		Mail mail = new Mail();
        mail.setMailFrom(configProperties.getMail());
        mail.setMailTo(customer.getEmail());
        mail.setMailSubject(OOMSConstants.CUSTOMER_ORDDER_HEADING);
        Map < String, Object > model = new HashMap < String, Object > ();
        
       List<OrderItemDto> items = order.getItems();
       model.put("id", order.getId());
       model.put("Items", items);
       model.put("OrderDate", order.getDateOfOrder().toString());
       model.put("name",customer.getName());
       model.put("total_cost", order.getAdvanceAmount()+order.getRemainingbalance());
       model.put("adavance_deposit",order.getAdvanceAmount());
       model.put("remain_balance",order.getRemainingbalance());
       model.put("tax", order.getTax());
       model.put("total_order_cost", order.getTotalCost());
       model.put("signature",configProperties.getSignature());
       model.put("address",configProperties.getAddress());
       mail.setModel(model);
       return mail;
		
	}

	@Override
	public ResponseBo addSupplier(String userId, Supplier supplier) {
		
		int id = Integer.parseInt(userId);
		supplier.setOnboardDate(new Date());
		User  user = userRepository.findOne(id);
		for (Supplier tempSupplier : user.getSuppliers()) {
			if(tempSupplier.getPhone().equals(supplier.getPhone()) || tempSupplier.getEmail().equalsIgnoreCase(supplier.getEmail())) {
				ResponseBo response = new ResponseBo();
				response.setErrorCode("200");
				response.setMessage(tempSupplier.getId()+"");
				return response;
			}
		}
		user.getSuppliers().add(supplier);
		userRepository.save(user);
		
		List<Supplier> suppliers  = supplierRepository.getSupplierByEmailAndMobile(supplier.getEmail(), supplier.getPhone());
		ResponseBo response = new ResponseBo();
		response.setErrorCode("200");
		response.setMessage(suppliers.get(0).getId()+"");
		
		return response;
	}

	@Override
	public List<SupplierDto> getSuppliersByUserId(String userId) {
		int id = Integer.parseInt(userId);
		List<Supplier> suppliers = userRepository.findOne(id).getSuppliers();
		List<SupplierDto> supplierDtos = new ArrayList<>();
		for (Supplier supplier : suppliers) {
			supplierDtos.add(supplierToSupplierDtoConvertor.convert(supplier));
		}
		return supplierDtos;
	}

	@Override
	public Customer getCustomerByIdAndUserId(String userId, String customerId) {
		
		//Here we have customerId and userID as well but for now we are fetching from customerid only
		//in future we can use both userid and customerid
		int uid = Integer.parseInt(userId);
		int cid = Integer.parseInt(customerId);
		return customerRepository.findOne(cid);
	}

	@Override
	public CustomerDto convertCustomerToCustomerDto(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setId(customer.getId());
		customerDto.setEmail(customer.getEmail());
		customerDto.setMobile(customer.getMobile());
		customerDto.setName(customer.getName());
		customerDto.setAddress(customer.getAddress());
		SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		if(null != customer.getOnboardDate()) {
			String dateText = df2.format(customer.getOnboardDate());
	        customerDto.setOnboardDate(dateText);
		}
		return customerDto;
	}

	@Override
	public ResponseBo deleteCustomerByUserIdAndId(String userId, String customerId) {
		int uid = Integer.parseInt(userId);
		int cid = Integer.parseInt(customerId);
		User user = userRepository.findOne(uid);
		ResponseBo response = new ResponseBo();
		Iterator itr = user.getCustomers().iterator();
		while(itr.hasNext()) {
			Customer customer = (Customer) itr.next();
			if(customer.getId() == cid) {
				itr.remove();
				response.setErrorCode("200");
				response.setMessage("Success");
				userRepository.save(user);
				return response;
			}
		}
		userRepository.save(user);
		return response;
	}

	@Override
	public HomeDto getHomeDto(String userId) {
		HomeDto homeDto = new HomeDto();
		int uid = Integer.parseInt(userId);
		int totalOrders = 0;
		int totalSupplierOrders = 0;
		User user = userRepository.findOne(uid);
		homeDto.setTotalCustomers(user.getCustomers().size());
		for (Customer customer : user.getCustomers()) {
			totalOrders = totalOrders + customer.getOrders().size();
		}
		homeDto.setTotalSuppliers(user.getSuppliers().size());
		homeDto.setTotalOrders(totalOrders);
		
		
        List<CustomerDto> customerDtos = new ArrayList<>(user.getCustomers().size());
        List<OrderDto> orderDtos = new ArrayList<>();
        List<SupplierDto> supplierDtos = new ArrayList<>(user.getSuppliers().size());
        List<PurchaseOrderDto> purchaseOrderDtos = new ArrayList<>();
        
        for(Customer customer : user.getCustomers()) {
        	
        	if(null != customer.getOnboardDate()) {
        		if(DateUtils.isSameDay(customer.getOnboardDate(), new Date())) {
    				customerDtos.add(convertCustomerToCustomerDto(customer));
    			}
    			
        	}
        	
        	for(Order order : customer.getOrders()) {
				if(null != order.getDateOfOrder()) {
					if(DateUtils.isSameDay(order.getDateOfOrder(), new Date())) {
    					orderDtos.add(orderService.convertoToOrderDto(order));
    				}
				}
				
			}
        	
			
		}
		
		for(Supplier supplier : user.getSuppliers()) {
			
			if(null != supplier.getOnboardDate()) {
				if(DateUtils.isSameDay(supplier.getOnboardDate(), new Date())) {
					supplierDtos.add(converSuppliertToSupplierDto(supplier));
				}
			}
			
			for(PurchaseOrder purchaseOrder : supplier.getOrders()) {
				totalSupplierOrders++;
				if(null!=purchaseOrder.getOrderDate()) {
					if(DateUtils.isSameDay(purchaseOrder.getOrderDate(), new Date())) {
						purchaseOrderDtos.add(purchaseOrderToPurchaseOrderDtoConvertor.convert(purchaseOrder));
						
					}
				}
			}
			
		}
		
		//get unread notification count
		int unreadNotifications = 0;
		List<Notification> notifications = user.getNotifications();
		for(Notification notification : notifications) {
			if(DateUtils.isSameDay(notification.getDate(), new Date())) {
				unreadNotifications++; 
			}
		}
		
		homeDto.setUnreadNotofications(unreadNotifications);
		homeDto.setTotalSupplierOrders(totalSupplierOrders);
		homeDto.setTodayscustomers(customerDtos);
		homeDto.setTodaysorders(orderDtos);
		homeDto.setTodayssuppliers(supplierDtos);
		homeDto.setTodaysSupplierseOrders(purchaseOrderDtos);
		return homeDto;
	}

	@Override
	public SupplierDto converSuppliertToSupplierDto(Supplier supplier) {
		SupplierDto supplierDto = new SupplierDto();
		supplierDto.setEmail(supplier.getEmail());
		supplierDto.setName(supplier.getName());
		supplierDto.setId(supplier.getId());
		supplierDto.setPhone(supplier.getPhone());
		supplierDto.setAddress(supplier.getAddress());
		SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		if(null != supplier.getOnboardDate()) {
			String dateText = df2.format(supplier.getOnboardDate());
	        supplierDto.setOnboardDate(dateText);
		}
		return supplierDto;
	}

	@Override
	public ResponseBo validateEmailAndPhone(String email, String phone) {
		List<User> users = new ArrayList<>();
		ResponseBo response = new ResponseBo();
		users = userRepository.validateUserByEmailAndPhone(email, phone);
		if(users.isEmpty() == false) {
			response.setErrorCode("500");
			response.setMessage("Email Or Mobile laready Exists");
			return response;
		}
		else {
			response.setErrorCode("200");
			response.setMessage("success");
			return response;
		}
	}

	@Override
	public Configuration getConfigurationByUserId(String userId) {
		int uid = Integer.parseInt(userId);
		User user = userRepository.findOne(uid);
		return user.getConfiguration();
	}

	@Override
	public ResponseBo addConfigurationByUserId(String userId, Configuration configuration) {
		int uid = Integer.parseInt(userId);
		User user = userRepository.findOne(uid);
		user.setConfiguration(configuration);
		userRepository.save(user);
		ResponseBo response = new ResponseBo();
		response.setErrorCode("200");
		response.setMessage("Succesfully updated configuration!!!");
		return response;
		
	}

	@Override
	public ResponseBo deleteItemFromCatalog(String userId, String itemId) {
		int uid = Integer.parseInt(userId);
		int iid = Integer.parseInt(itemId);
		User user = userRepository.findOne(uid);
		ResponseBo response = new ResponseBo();
		Catalog catalog = user.getCatalog();
		if(null!=catalog) {
			for(ProductItem productItem : catalog.getItems()) {
				if(productItem.getId() == iid) {
					productItem.setStatus("INACTIVE");
					user.setCatalog(catalog);
					userRepository.save(user);
					response.setErrorCode("200");
					response.setMessage("Success");
					return response;
				}
				
			}
		}
		return response;
	}

	@Override
	public ResponseBo updateItemFromCatalog(String userId, String itemId,ProductItem productItem) {
		int uid = Integer.parseInt(userId);
		int iid = Integer.parseInt(itemId);
		ResponseBo response = new ResponseBo();
		User user = userRepository.findOne(uid);
		Catalog catalog = user.getCatalog();
		for(ProductItem item : catalog.getItems()) {
			if(item.getId() == iid) {
				item.setBrand(productItem.getBrand());
				item.setDescription(productItem.getDescription());
				item.setName(productItem.getName());
				item.setPrice(productItem.getPrice());
				item.setQuantity(productItem.getQuantity());
				item.setUOM(productItem.getUOM());
				catalog.getItems().add(productItem);
				user.setCatalog(catalog);
				userRepository.save(user);
				response.setErrorCode("200");
				response.setMessage("success");
				return response;
			}
		}
		return response;
	}

	@Override
	public ResponseBo updateCustomer(String userId, String customerId,Customer customer) {
		int uid = Integer.parseInt(userId);
		int cid = Integer.parseInt(customerId);
		ResponseBo response = new ResponseBo();
		User user = userRepository.findOne(uid);
		for(Customer customer1 : user.getCustomers()) {
			if(customer1.getId() == cid) {
				customer.setId(cid);
				customer.setOnboardDate(customer1.getOnboardDate());
				user.getCustomers().remove(customer1);
				user.getCustomers().add(customer);
				userRepository.save(user);
				response.setErrorCode("200");
				response.setMessage("customer updated succesfully");
				return response;
			}
		}
		
		response.setErrorCode("500");
		response.setMessage("User mnot found");
		return response;
	}

	@Override
	public ResponseBo deleteOrder(String userId, String customerId,String orderId) {
		
		int uid = Integer.parseInt(userId);
		int cid = Integer.parseInt(customerId);
		int oid = Integer.parseInt(orderId);
		User user = userRepository.findOne(uid);
		Set<Order> orders = new HashSet<>();
		for (Customer customer: user.getCustomers() ) {
			if(customer.getId() == cid) {
				for (Order order : customer.getOrders()) {
					if(order.getId() != oid) {
						orders.add(order);
					}
				}
				customer.setOrders(orders);
			}
			
			
		}
		userRepository.save(user);
		return orderService.deleteOrder(oid);
		
	}

	@Override
	public ResponseBo deleteSupplier(String userId, String supplierId) {
		int uid = Integer.parseInt(userId);
		int sid = Integer.parseInt(supplierId);
		ResponseBo response = new ResponseBo();
		User user = userRepository.findOne(uid);
		for (Supplier supplier : user.getSuppliers()) {
			if(supplier.getId() == sid) {
				user.getSuppliers().remove(supplier);
				response.setErrorCode("200");
				response.setMessage("Supplier deleted succesfully");
				userRepository.save(user);
				return response;
			}
		}
		response.setErrorCode("400");
		response.setMessage("Supplier not found");
		return response;
	}

	@Override
	public ResponseBo updateSupplier(String userId, String supplierId,Supplier newSupplierDetails) {
		int uid = Integer.parseInt(userId);
		int sid = Integer.parseInt(supplierId);
		ResponseBo response = new ResponseBo();
		User user = userRepository.findOne(uid);
		for (Supplier supplier : user.getSuppliers()) {
			if(supplier.getId() == sid) {
				user.getSuppliers().remove(supplier);
				user.getSuppliers().add(newSupplierDetails);
				response.setErrorCode("200");
				response.setMessage("Supplier updated succesfully");
				userRepository.save(user);
				return response;
			}
		}
		response.setErrorCode("400");
		response.setMessage("Suppliernot found");
		return response;
	}

	@Override
	public SupplierDto getSupplierDetailsById(String supplierId) {
		int sid = Integer.parseInt(supplierId);
		Supplier supplier = supplierRepository.findOne(sid);
		return supplierToSupplierDtoConvertor.convert(supplier);
		
	}

	@Override
	public ResponseBo deletePurchaseOrderById(String supplierId, String POid) {
		int sid = Integer.parseInt(supplierId);
		int pid = Integer.parseInt(POid);
		ResponseBo response = new ResponseBo();
		Supplier supplier = supplierRepository.findOne(sid);
		for (PurchaseOrder order : supplier.getOrders()) {
			if(order.getId() == pid) {
				supplier.getOrders().remove(order);
				supplierRepository.save(supplier);
				response.setErrorCode("200");
				response.setMessage("Purchase Order deleted succesfully");
				return response;
			}
		}
		response.setErrorCode("400");
		response.setMessage("Purchase Order not found");
		return response;
	}

	@Override
	public PurchaseOrderDto getPurchaseOrderById(String supplierId, String poid) {
		int sid = Integer.parseInt(supplierId);
		int pid = Integer.parseInt(poid);
		Supplier supplier = supplierRepository.findOne(sid);
		for (PurchaseOrder poOrder : supplier.getOrders()) {
			
			if(poOrder.getId() == pid) {
				return purchaseOrderToPurchaseOrderDtoConvertor.convert(poOrder);
			}
		}
		return new PurchaseOrderDto();
	}

	@Override
	public List<NotificationDto> getAllNotificationsByUserId(String userId) {
		int uid = Integer.parseInt(userId);
		User user = userRepository.findOne(uid);
		List<Notification> notifications = user.getNotifications();
		List<NotificationDto> notificationDtos = new ArrayList<>(notifications.size());
		Collections.sort(notifications, new SortNotificationsByDateComparator());
		for(Notification notification :notifications) {
			notificationDtos.add(notificationToNotificatioDtoConvertor.convert(notification));
		}
		return notificationDtos;
	}

	@Override
	public UserDto getProfileByUserId(String userId) {
		int uid = Integer.parseInt(userId);
		User user = userRepository.findOne(uid);
		return userToUserDtoConvertor.convert(user);
	}


}
