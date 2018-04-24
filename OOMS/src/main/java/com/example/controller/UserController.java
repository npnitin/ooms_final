
package com.example.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.bo.Catalog;
import com.example.bo.Configuration;
import com.example.bo.Credential;
import com.example.bo.Customer;
import com.example.bo.Order;
import com.example.bo.ProductItem;
import com.example.bo.PurchaseOrder;
import com.example.bo.ResponseBo;
import com.example.bo.ServiceRequest;
import com.example.bo.Supplier;
import com.example.bo.Tax;
import com.example.bo.User;
import com.example.config.BasicConfig;
import com.example.dao.ServiceRequestRepository;
import com.example.dao.UserRepository;
import com.example.dto.CustomerDto;
import com.example.dto.HomeDto;
import com.example.dto.NotificationDto;
import com.example.dto.OrderDto;
import com.example.dto.PurchaseOrderDto;
import com.example.dto.SupplierDto;
import com.example.dto.TaxDto;
import com.example.dto.UserDto;
import com.example.mailutil.Mail;
import com.example.mailutil.MailService;
import com.example.service.OrderService;
import com.example.service.PurchaseOrderService;
import com.example.service.SupplierService;
import com.example.service.TaxService;
import com.example.service.UserService;
import com.example.utility.OOMSConstants;
import com.example.utility.PurchaseOrderToPurchaseOrderDtoConvertor;
import com.example.utility.SortingUtility;
import com.example.utility.SupplierToSupplierDtoConvertor;

@RestController
@RequestMapping(value="/user")
public class UserController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BasicConfig configProperties;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired 
	UserService userService;
	
	@Autowired 
	OrderService orderService;
	
	@Autowired 
	SupplierService supplierService;
	
	@Autowired
	SupplierToSupplierDtoConvertor supplierToSupplierDtoConvertor;
	
	@Autowired
	PurchaseOrderToPurchaseOrderDtoConvertor purchaseOrderToPurchaseOrderDtoConvertor;
	
	@Autowired
	SortingUtility sortingUtility;
	
	@Autowired
	ServiceRequestRepository serviceRequestRepository;
	
	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	
	@Autowired
	TaxService taxService;
	
	@Autowired
	MailService mailService;
	
	
	@PostMapping(value = "/createUser")
	public @ResponseBody ResponseBo createuser(@RequestBody User user) {
		user.setStatus(0);
		user.setDateOfRegistration(new Date());
		user = userRepository.save(user);
		Mail mail = createMail(user);
		mailService.sendEmail(mail,OOMSConstants.SEND_ACC_ACTIVATION_STRING);
		ResponseBo response = new ResponseBo();
		response.setErrorCode("200");
		response.setMessage("success");
		return response;
	}
	
	private Mail createMail(User user) {
		Mail mail = new Mail();
        mail.setMailFrom(configProperties.getMail());
        mail.setMailTo(user.getEmail());
        mail.setMailSubject(OOMSConstants.EMAIL_ACC_ACTIVATION_HEADING);
        String url = OOMSConstants.URL_PREFIX+configProperties.getHost()+OOMSConstants.ACC_ACTIVATION_URL+user.getId();
        Map < String, Object > model = new HashMap < String, Object > ();
        model.put("Name", user.getName());
        model.put("URL", url);
        model.put("location", configProperties.getAddress());
        model.put("signature", configProperties.getSignature());
        mail.setModel(model);
        return mail;
	}

	@PostMapping(value = "/doLogin")
	public @ResponseBody ResponseBo doLogin(@RequestBody Credential credential) {
		
		return userService.doLogin(credential);
		
	}
	
	@PostMapping(value = "/doLogout")
	public String doLogout(@RequestBody Credential credential) {
		
		return userService.doLogout(credential);
		
	}
	
	@PostMapping(value = "/autheticateUser")
	public String authenticateUser(Credential credential) {
		return userService.authenticateUser(credential);
		
	}
	
	@GetMapping(value = "/getAllCustomersByUserId")
	public @ResponseBody List<CustomerDto> getAllCustomersByUserId(@RequestParam("userId") String userId) {
		int id =Integer.parseInt(userId);
		List<Customer> customers = userRepository.getOne(id).getCustomers();
		List<CustomerDto> customerDtos = new ArrayList<>();
		for(Customer customer : customers) {
			customerDtos.add(userService.convertCustomerToCustomerDto(customer));
		}
		if(customerDtos != null) {
			return customerDtos;
		}
		else {
			return new ArrayList<>();
		}
	}

	@GetMapping(value = "/getAllSuppliersByUserId")
	public @ResponseBody List<Supplier> getAllSuppliersByUserId(@RequestParam("userId") String userId) {
		int id =Integer.parseInt(userId);
		List<Supplier> suppliers = userRepository.getOne(id).getSuppliers();
		if(suppliers != null) {
			return suppliers;
		}
		else {
			return new ArrayList<>();
		}
	}
	
	@GetMapping(value = "/getAllOrdersByUserId")
	public @ResponseBody List<Order> getAllOrdersByUserId(@RequestParam("userId") String userId) {
		
		List<Order> orders = userService.getAllOrdersByUserId(userId);
		if(orders != null) {
			return orders;
		}
		else {
			return new ArrayList<>();
		}
	}
	@GetMapping(value = "/getCatalogByUserId")
	public @ResponseBody Catalog getCatalogByUserId(@RequestParam("userId") String userId) {
		return userService.getCatalogByUserId(userId);
	}
	
	@PostMapping(value = "/addItemToCatalog")
	public @ResponseBody ResponseBo addItemToCatalog(@RequestParam("userId") String userId,@RequestBody ProductItem item){
		return userService.addItemToCatalog(userId, item);
	}
	@GetMapping(value = "/getCustomersByUserId")
	public @ResponseBody List<CustomerDto> getCustomersByUserId(@RequestParam("userId") String userId) {
		List<Customer> customers = userService.getCustomersByUserId(userId);
		List<CustomerDto> customerDtos = new ArrayList<>();
		for(Customer customer : customers) {
			customerDtos.add(userService.convertCustomerToCustomerDto(customer));
		}
		if(customerDtos != null) {
			return customerDtos;
		}
		else {
			return new ArrayList<>();
		}
	}
	@PostMapping(value = "/addCustomer")
	public @ResponseBody ResponseBo addCustomer(@RequestParam("userId") String userId,@RequestBody Customer customer){
		return userService.addCustomer(userId, customer);
	}
	
	@GetMapping(value = "/getOrdersByUserId")
	public @ResponseBody List<OrderDto> getOrdersByUserId(@RequestParam("userId") String userId) {
		List<Order> orders =  userService.getOrdersByUserId(userId);
		List<OrderDto> ordersToReturn = new ArrayList<>(orders.size());
		for (Order order : orders) {
			ordersToReturn.add(orderService.convertoToOrderDto(order));
		}
		 return ordersToReturn;
	}
	@PostMapping(value = "/addOrder")
	public @ResponseBody ResponseBo addOrder(@RequestParam("userId") String userId,@RequestParam("customerId") String customerId,@RequestBody Order order){
		return userService.addOrder(userId,customerId, order);
	}
	
	@GetMapping(value = "/getOrderDetailsByOrderId")
	public @ResponseBody OrderDto getOrderDetailsByOrderId(@RequestParam("orderId") String orderId) {
		int id = Integer.parseInt(orderId);
		Order order = orderService.getOrderDetailsById(id);
		OrderDto orderDto = orderService.convertoToOrderDto(order);
		return orderDto;
	}
	@PostMapping(value = "/addSupplier")
	public @ResponseBody ResponseBo addSupplier(@RequestParam("userId") String userId,@RequestBody Supplier supplier) {
		return userService.addSupplier(userId, supplier);
	}
	@GetMapping(value = "/getSuppliersByUserId")
	public @ResponseBody List<SupplierDto> getSuppliersByUserId(@RequestParam("userId") String userId){
		return userService.getSuppliersByUserId(userId);
	}
	@GetMapping(value = "/getCustomerByIdAndUserId")
	public @ResponseBody CustomerDto getCustomerByIdAndUserId(@RequestParam("userId") String userId,@RequestParam("customerId") String customerId) {
		
		Customer customer =  userService.getCustomerByIdAndUserId(userId, customerId);
		CustomerDto customerDto = userService.convertCustomerToCustomerDto(customer);
		Set<OrderDto> orders = new HashSet<>(customer.getOrders().size());
		for(Order order : customer.getOrders()) {
			orders.add(orderService.convertoToOrderDto(order));
		}
		customerDto.setOrders(orders);
		return customerDto;
	}
	@GetMapping(value = "/deleteCustomerByUserIdAndId")
	public @ResponseBody ResponseBo deleteCustomerByUserIdAndId(@RequestParam("userId") String userId,@RequestParam("customerId") String customerId){
		return userService.deleteCustomerByUserIdAndId(userId, customerId);
	}
	@GetMapping(value  = "/getHomeData")
	public @ResponseBody HomeDto getHomeData(@RequestParam("userId") String userId) {
		return userService.getHomeDto(userId);
	}
	@GetMapping(value = "/validateEmailAndPhone")
	public @ResponseBody ResponseBo validateEmailAndPhone(@RequestParam("email") String email,@RequestParam("phone") String phone) {
		return userService.validateEmailAndPhone(email, phone);
	}
	@GetMapping("/getConfigurationByUserId")
	public @ResponseBody Configuration getConfigurationByUserId(@RequestParam("userId") String userId){
		return userService.getConfigurationByUserId(userId);
	}
	@PostMapping("/addConfigurationByUserId")
	public @ResponseBody ResponseBo addConfigurationByUserId(@RequestParam("userId") String userId,@RequestBody Configuration configuration) {
		return userService.addConfigurationByUserId(userId, configuration);
	}
	@GetMapping("/changeOrderStatusToRTD")
	public @ResponseBody ResponseBo changeOrderStatusToRTD(@RequestParam("orderId") String orderId) {
		return orderService.changeOrderStatusToReadyToDispacth(orderId);
	}
	@GetMapping("/changeOrderStatusToClose")
	public @ResponseBody ResponseBo changeOrderStatusToClose(@RequestParam("userId") String userId,@RequestParam("orderId") String orderId) {
		return orderService.changeOrderStatusToClose(userId,orderId);
	}
	
	@GetMapping("/getAllPurchaseOrdersByUserId")
	public @ResponseBody List<PurchaseOrderDto> getAllPurchaseOrdersByUserId(@RequestParam("userId") String userId){
		 List<PurchaseOrder> purchaseOrders = supplierService.getAllPurchaseOrdersByUserId(userId);
		 List<PurchaseOrderDto> purchaseOrdersToReturn = new ArrayList<>();
		 for (PurchaseOrder purchaseOrder : purchaseOrders) {
			purchaseOrdersToReturn.add(purchaseOrderToPurchaseOrderDtoConvertor.convert(purchaseOrder));
		}
		 return purchaseOrdersToReturn;
	}
	
	@PostMapping("/addPurchaseOrder")
	public @ResponseBody ResponseBo addPurchaseOrder(@RequestParam("userId") String userId,@RequestParam("supplierId") String supplierId,@RequestBody PurchaseOrder purchaseOrder) {
		return supplierService.addPurchaseOrder(userId,supplierId, purchaseOrder);
	}
	
	@GetMapping("/deleteItemFromCatalog")
	public @ResponseBody ResponseBo deleteItemFromCatalog(@RequestParam("userId") String userId,@RequestParam("itemId") String itemId) {
		return userService.deleteItemFromCatalog(userId, itemId);
	}
	@PostMapping("/updateItemFromCatalog")
	public @ResponseBody ResponseBo updateItemFromCatalog(@RequestParam("userId") String userId,@RequestParam("itemId") String itemId,@RequestBody ProductItem productItem) {
		return userService.updateItemFromCatalog(userId, itemId, productItem);
	}
	@PostMapping("/updateCustomer")
	public @ResponseBody ResponseBo updatedCustomer(@RequestParam("userId") String userId,@RequestParam("customerId") String customerId,@RequestBody Customer customer) {
		return userService.updateCustomer(userId, customerId, customer);
	}
	@GetMapping("/deleteOrder")
	public @ResponseBody ResponseBo deleteOrder(@RequestParam("userId") String userId,@RequestParam("customerId") String customerId,@RequestParam("orderId") String orderId) {
		return userService.deleteOrder(userId, customerId,orderId);
	}
	
	@GetMapping("/deleteSupplier")
	public @ResponseBody ResponseBo deleteSupplier(@RequestParam("userId") String userId,@RequestParam("supplierId") String supplierId) {
		return userService.deleteSupplier(userId, supplierId);
	}
	@PostMapping("/updateSupplier")
	public @ResponseBody ResponseBo updateSupplier(@RequestParam("userId") String userId,@RequestParam("supplierId") String supplierId,@RequestBody Supplier supplier) {
		return userService.updateSupplier(userId, supplierId, supplier);
	}
	
	@GetMapping("/getSupplierDetailsById")
	public @ResponseBody SupplierDto getSupplierDetailsById(@RequestParam("supplierId") String supplierId) {
		return userService.getSupplierDetailsById(supplierId);
	}
	
	@GetMapping("/deletePurchaseOrderById")
	public @ResponseBody ResponseBo deletePurchaseOrderById(@RequestParam("supplierId") String supplierId,@RequestParam("poid") String purchaseOrderId){
		return userService.deletePurchaseOrderById(supplierId, purchaseOrderId);
	}
	
	@GetMapping("/getPurchaseOrderdetailsById")
	public PurchaseOrderDto getPurchaseOrderdetailsById(@RequestParam("supplierId") String supplierId,@RequestParam("poId") String purchaseOrderId){
		return userService.getPurchaseOrderById(supplierId, purchaseOrderId);
	}
	
	@GetMapping("/sortData")
	public @ResponseBody List<Object> sortData(@RequestParam("userId") String userId,@RequestParam("attr") String attribute,@RequestParam("entity") String entity){
		return sortingUtility.sortData(attribute, entity,userId);
	}
	@PostMapping("/addServiceRequest")
	public @ResponseBody ResponseBo addServiceRequest(@RequestParam("userId") String userId,@RequestBody ServiceRequest serviceRequest) {
		int uid = Integer.parseInt(userId);
		User user = userRepository.findOne(uid);
		ResponseBo response = new ResponseBo();
		if(user!=null) {
			response.setErrorCode("200");
			response.setMessage("request Submitted succesfully");
			serviceRequestRepository.save(serviceRequest);
			return response;
		}
		response.setErrorCode("400");
		response.setMessage("Invalid request");
		return response;
	}
	@GetMapping("/getNotificationsByUserId")
	public @ResponseBody List<NotificationDto> getNotificationsByUserId(@RequestParam("userId") String userId){
		return userService.getAllNotificationsByUserId(userId);
	}
	
	@GetMapping("/changePurchaseOrderStatus")
	public @ResponseBody ResponseBo changePurchaseOrderStatus(@RequestParam("userId") String userId,@RequestParam("orderId") String orderId,@RequestParam("status") String status) {
		return purchaseOrderService.changeOrderStatus(userId,orderId, status);
	}
	@GetMapping("/getProfileByUserId")
	public @ResponseBody UserDto getProfileByUserId(@RequestParam("userId") String userId) {
		return userService.getProfileByUserId(userId);
	}
	
	@PostMapping("/addTaxByUser")
	public @ResponseBody ResponseBo addTaxByUser(@RequestParam("userId") String userId,@RequestBody Tax tax) {
		return taxService.addTax(userId, tax);
	}
	
	@GetMapping("/getAllTaxesByUserId")
	public @ResponseBody List<TaxDto> getAllTaxesByUserId(@RequestParam("userId") String userId){
		return taxService.getAllTaxesByUserId(userId);
	}
	
	@GetMapping("/deleteTaxById")
	public @ResponseBody ResponseBo deleteTaxById(@RequestParam("userId") String userId,@RequestParam("taxId") String taxId) {
		return taxService.deleteTaxById(userId, taxId);
	}
	@PostMapping("/updateTaxById")
	public @ResponseBody ResponseBo updateTaxById(@RequestParam("userId") String userId,@RequestParam("taxId") String taxId,@RequestBody Tax tax) {
		return taxService.updateTax(userId, taxId, tax);
	}
	
	@GetMapping("/activateAccount")
	public RedirectView activateUserAccount(@RequestParam("userId") String userId){
		int uid = Integer.parseInt(userId);
		User user = userRepository.findOne(uid);
		user.setStatus(1);
		userRepository.save(user);
		return new RedirectView(OOMSConstants.URL_PREFIX+configProperties.getHost()+"/"+OOMSConstants.LOGIN_PAGE);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
