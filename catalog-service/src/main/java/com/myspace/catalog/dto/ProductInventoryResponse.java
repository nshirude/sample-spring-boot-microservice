package com.myspace.catalog.dto;

import lombok.Data;

@Data
public class ProductInventoryResponse {

	private String productCode;
    private int availableQuantity;
}
