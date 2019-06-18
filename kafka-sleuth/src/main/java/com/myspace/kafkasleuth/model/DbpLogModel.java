package com.myspace.kafkasleuth.model;

public class DbpLogModel {

	private String transactionId;
	private String responseTransactionId;
	private String serviceName;
	private Long serviceId;
	private Long clientId;
	private String clientName;
	private String userSourceIp;
	private String status;
	private String request;
	private String response;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getResponseTransactionId() {
		return responseTransactionId;
	}

	public void setResponseTransactionId(String responseTransactionId) {
		this.responseTransactionId = responseTransactionId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getUserSourceIp() {
		return userSourceIp;
	}

	public void setUserSourceIp(String userSourceIp) {
		this.userSourceIp = userSourceIp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
