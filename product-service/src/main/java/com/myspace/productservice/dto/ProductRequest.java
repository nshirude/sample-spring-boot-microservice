package com.myspace.productservice.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductRequest {
	
	@NotNull(message="productCode cannot be empty")
    @JsonProperty("productCode")
	private String productCode;
	
	@NotNull(message="availableQuantity cannot be empty")
    @JsonProperty("availableQuantity")
    private int availableQuantity;

}
