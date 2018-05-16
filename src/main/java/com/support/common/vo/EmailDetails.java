package com.support.common.vo;


import java.io.Serializable;
import java.util.List;


/**
 * @author xbbnbg3
 *
 */
public class EmailDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3858592566134153293L;
	private String sender;
	private String recipients;
	private String subject;
	private String body;
	private String host;
	private String port;
	private String auth;
	private String userName;
	private String password;
	private String starttlsEnable;
	private String carbonCopy;
	List<EmailAttachment> emailAttachments;
	private boolean hasEnoughInfoToSendEmail;
	/**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}
	/**
	 * @param sender the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * @return the recipients
	 */
	public String getRecipients() {
		return recipients;
	}
	/**
	 * @param recipients the recipients to set
	 */
	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}
	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}
	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}
	/**
	 * @return the auth
	 */
	public String getAuth() {
		return auth;
	}
	/**
	 * @param auth the auth to set
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the starttlsEnable
	 */
	public String getStarttlsEnable() {
		return starttlsEnable;
	}
	/**
	 * @param starttlsEnable the starttlsEnable to set
	 */
	public void setStarttlsEnable(String starttlsEnable) {
		this.starttlsEnable = starttlsEnable;
	}
	/**
	 * @return the carbonCopy
	 */
	public String getCarbonCopy() {
		return carbonCopy;
	}
	/**
	 * @param carbonCopy the carbonCopy to set
	 */
	public void setCarbonCopy(String carbonCopy) {
		this.carbonCopy = carbonCopy;
	}
	/**
	 * @return the emailAttachments
	 */
	public List<EmailAttachment> getEmailAttachments() {
		return emailAttachments;
	}
	/**
	 * @param emailAttachments the emailAttachments to set
	 */
	public void setEmailAttachments(List<EmailAttachment> emailAttachments) {
		this.emailAttachments = emailAttachments;
	}
	public boolean hasEnoughInfoToSendEmail() {
		return hasEnoughInfoToSendEmail;
	}
	
	public void setHasEnoughInfoToSendEmail(boolean hasEnoughInfoToSendEmail) {
		this.hasEnoughInfoToSendEmail = hasEnoughInfoToSendEmail;
	}
	
	

	
}
