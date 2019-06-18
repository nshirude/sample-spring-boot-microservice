package com.myspace.catalog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.myspace.catalog.controller.CatalogController;
import com.myspace.catalog.dto.ProductInventoryResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CatalogService {
	
	@Autowired
	RestTemplate restTempalte;
	
	@HystrixCommand(commandKey = "catalog-by-productcode", fallbackMethod = "getDefaultProductCatalog")
	public Optional<ProductInventoryResponse> getProductCatalog(String productCode){
		log.info("getProductCatalog based on product code  :"+productCode);		
		ProductInventoryResponse productResponse = null;
		ResponseEntity<ProductInventoryResponse> itemResponseEntity = restTempalte.getForEntity("http://product-service/api/products/{code}",ProductInventoryResponse.class,productCode);		
		if(itemResponseEntity.getStatusCode().equals(HttpStatus.OK)){
			 return Optional.ofNullable(itemResponseEntity.getBody());				
		}else{
			return Optional.empty();
		}
			
	}
	
    Optional<ProductInventoryResponse> getDefaultProductCatalog(String productCode) {
        log.info("Returning default ProductInventoryByCode for productCode: "+productCode);
		//System.out.println("Returning default ProductInventoryByCode for productCode: "+productCode);
        ProductInventoryResponse response = new ProductInventoryResponse();
        response.setProductCode(productCode);
        response.setAvailableQuantity(50);
        return Optional.ofNullable(response);
    }

}
