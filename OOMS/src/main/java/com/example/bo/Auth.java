package com.example.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name ="OOMS_AUTH_TOKEN")
public class Auth {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="TOKEN")
	private String token;
	
	@Column(name="TIME_OF_TOKEN_ISSUED")
	private Date timeOfTokenIssued;
	
	@Column(name="TOKEN_VALID_TIME")
	private int tokenValidTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public Date getTimeOfTokenIssued() {
		return timeOfTokenIssued;
	}
	public void setTimeOfTokenIssued(Date timeOfTokenIssued) {
		this.timeOfTokenIssued = timeOfTokenIssued;
	}
	public int getTokenValidTime() {
		return tokenValidTime;
	}
	public void setTokenValidTime(int tokenValidTime) {
		this.tokenValidTime = tokenValidTime;
	}
	
	
	

}
