package com.myspace.productservice.dto;

import lombok.Data;

@Data
public class ProductResponseDto {
	//@JsonUnwrapped
	//private Product product;
	
	private String productCode;
    private int availableQuantity;
    
    public ProductResponseDto(){
    	
    }
    
	public ProductResponseDto(String productCode, int availableQuantity) {
		super();
		this.productCode = productCode;
		this.availableQuantity = availableQuantity;
	}
}
