package com.myspace.productservice.service;

import org.springframework.stereotype.Service;

import com.myspace.productservice.dto.ProductResponseDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	public ProductResponseDto findProductByCode(String code){
		log.info("findProductByCode :"+code);
		ProductResponseDto productResponse= new ProductResponseDto();
		productResponse.setProductCode("Pencil");
		productResponse.setAvailableQuantity(200);
		return productResponse;
	}
}
