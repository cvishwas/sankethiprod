package com.support.common.beans;

import java.util.Date;

public class SmartdocsUser {
	
	private String userId;
	private String firstName;
	private String lastName;
	private String accType;
	private String clientId;
	private String clientName;
	private String region;
	private String wrhsCd;
	private String vltId;
	private String roles;
	private String enabled;
	private String createdBy;
	private Date createdDate;
	
		
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getWrhsCd() {
		return wrhsCd;
	}
	public void setWrhsCd(String wrhsCd) {
		this.wrhsCd = wrhsCd;
	}
	public String getVltId() {
		return vltId;
	}
	public void setVltId(String vltId) {
		this.vltId = vltId;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	@Override
	public String toString() {
		return "SmardocsUser [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", accType="
				+ accType + ", clientId=" + clientId + ", clientName=" + clientName + ", region=" + region + ", wrhsCd="
				+ wrhsCd + ", vltId=" + vltId + ", roles=" + roles + ", enabled=" + enabled + "]";
	}
	
	

}
