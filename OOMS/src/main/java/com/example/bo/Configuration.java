package com.example.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "OOMS_CONFIGURATION")
public class Configuration {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	
	@Column(name="RECEIVEDORDEREMAILSETTING")
	private boolean receivedOrderEmailSetting;
	
	@Column(name="RECEIVEDORDERSMSSETTING")
	private boolean receivedOrderSMSSetting;
	
	@Column(name="ORDERREADYTODISPATCHEMAILSETTING")
	private boolean orderReadyToDispatchEMAIlSetting;
	
	@Column(name="ORDERREADYTODISPATCHSMSSETTING")
	private boolean orderReadyToDispatchSMSSetting;
	
	@Column(name="ITEMTHRESHHOLDVAL")
    @ColumnDefault("'10'")
	private int productItemThreshHoldValue;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isReceivedOrderEmailSetting() {
		return receivedOrderEmailSetting;
	}
	public void setReceivedOrderEmailSetting(boolean receivedOrderEmailSetting) {
		this.receivedOrderEmailSetting = receivedOrderEmailSetting;
	}
	public boolean isReceivedOrderSMSSetting() {
		return receivedOrderSMSSetting;
	}
	public void setReceivedOrderSMSSetting(boolean receivedOrderSMSSetting) {
		this.receivedOrderSMSSetting = receivedOrderSMSSetting;
	}
	public boolean isOrderReadyToDispatchEMAIlSetting() {
		return orderReadyToDispatchEMAIlSetting;
	}
	public void setOrderReadyToDispatchEMAIlSetting(boolean orderReadyToDispatchEMAIlSetting) {
		this.orderReadyToDispatchEMAIlSetting = orderReadyToDispatchEMAIlSetting;
	}
	public boolean isOrderReadyToDispatchSMSSetting() {
		return orderReadyToDispatchSMSSetting;
	}
	public void setOrderReadyToDispatchSMSSetting(boolean orderReadyToDispatchSMSSetting) {
		this.orderReadyToDispatchSMSSetting = orderReadyToDispatchSMSSetting;
	}
	public int getProductItemThreshHoldValue() {
		return productItemThreshHoldValue;
	}
	public void setProductItemThreshHoldValue(int productItemThreshHoldValue) {
		this.productItemThreshHoldValue = productItemThreshHoldValue;
	}
	
	
	
}
