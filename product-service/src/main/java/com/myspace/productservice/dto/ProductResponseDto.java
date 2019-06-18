package com.myspace.productservice.dto;

import lombok.Data;

@Data
public class ProductResponseDto {
	private String productCode;
    private int availableQuantity;
}
