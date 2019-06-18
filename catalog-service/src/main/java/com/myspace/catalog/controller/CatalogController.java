package com.myspace.catalog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.myspace.catalog.dto.CatalogResponseDto;
import com.myspace.catalog.dto.ProductInventoryResponse;
import com.myspace.catalog.service.CatalogService;

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/catalog-v1")
@Slf4j
public class CatalogController {
	
	@Autowired
	CatalogService catalogService;
	
	@Autowired
	  private Tracer tracer;
	
	
	
	@GetMapping("/{code}")
    public ResponseEntity<CatalogResponseDto> productByCode(@PathVariable String code) {
		tracer.currentSpan().context().traceIdString();
        log.info("Fetching Calalog based on product code  :"+code);		
		Optional<ProductInventoryResponse> productResponse = catalogService.getProductCatalog(code);
		if(productResponse.isPresent()){
			CatalogResponseDto catalogResponseDto= new CatalogResponseDto();
			catalogResponseDto.setProductCatagory("Stationary");
			catalogResponseDto.setProdcutName(productResponse.get().getProductCode());
			catalogResponseDto.setProductQuantity(productResponse.get().getAvailableQuantity());
			return new ResponseEntity<>(catalogResponseDto, HttpStatus.OK);		
		}else {
			 return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
       
    }

}
