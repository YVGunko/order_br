package com.yg.stpls.model.outDoorOrder;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yg.stpls.model.customer.Customer;
import com.yg.stpls.model.division.Division;
import com.yg.stpls.model.user.User;

@Entity
public class OutDoorOrder {
	@Access(AccessType.PROPERTY) @Id private String id;
	private String comment;
	private Boolean sample;
	
	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@Column(name = "client_id", columnDefinition="VARCHAR(255) default '0'")
	private Customer client ;
	
	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@Column(name = "division_code", columnDefinition="VARCHAR(255) default '0'")
	private Division division ;
	
	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@Column(name = "user_id", columnDefinition="BIGINT(20) default '0'")
	private User user ;
	
	@JsonFormat(pattern="dd.MM.yyyy HH:mm:ss",timezone="Europe/Moscow")
	private Date date ;
	
	@JsonFormat(pattern="dd.MM.yyyy HH:mm:ss",timezone="Europe/Moscow")
	private Date sentToMasterDate;
	
	@JsonFormat(pattern="dd.MM.yyyy HH:mm:ss",timezone="Europe/Moscow")
	private Date receivedFromMobileDate;
	
	public OutDoorOrder(String id, String comment, Date date, Date receivedFromMobileDate, Date sentToMasterDate, 
			Division division, User user, Customer client, Boolean sample) {
		super();
		this.id = id;
		this.user = user;
		this.comment = comment;
		this.date = date;
		this.receivedFromMobileDate = receivedFromMobileDate;
		this.sentToMasterDate = sentToMasterDate;
		this.division = division;
		this.client = client;
		this.sample = sample;
	}
	
	public OutDoorOrder(String id, String comment, Date date,  
			String division_code, Long idUser, String client_id, Boolean sample) {
		super();
		this.id = id;
		this.user = new User (idUser);
		this.comment = comment;
		this.date = date;
		this.receivedFromMobileDate = new Date();
		this.division = new Division (division_code);
		this.client = new Customer (client_id);
		this.sample = sample;
	}
	
	public Boolean getSample() {
		return sample;
	}

	public void setSample(Boolean sample) {
		this.sample = sample;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Customer getCustomer() {
		return client;
	}
	public void setCustomer(Customer client) {
		this.client = client;
	}
	public Division getDivision() {
		return division;
	}
	public void setDivision(Division division) {
		this.division = division;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getSentToMasterDate() {
		return sentToMasterDate;
	}
	public void setSentToMasterDate(Date sentToMasterDate) {
		this.sentToMasterDate = sentToMasterDate;
	}
	public Date getReceivedFromMobileDate() {
		return receivedFromMobileDate;
	}
	public void setReceivedFromMobileDate(Date receivedFromMobileDate) {
		this.receivedFromMobileDate = receivedFromMobileDate;
	}
	
	public OutDoorOrder() {
		super();
	}
}
