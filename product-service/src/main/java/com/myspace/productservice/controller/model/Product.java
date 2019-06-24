package com.myspace.productservice.controller.model;

public class Product {

	private Integer productId;
	private String productCode;
	private Integer availableQuantity;

	public Product(){
		
	}
	
	public Product(Integer productId, String productCode, Integer availableQuantity) {
		super();
		this.productId = productId;
		this.productCode = productCode;
		this.availableQuantity = availableQuantity;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

}
