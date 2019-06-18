package com.myspace.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myspace.productservice.dto.ProductResponseDto;
import com.myspace.productservice.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/{code}")
    public ResponseEntity<ProductResponseDto> productByCode(@PathVariable String code) {
        log.info("Finding product by code :"+code);
        return new ResponseEntity<>(productService.findProductByCode(code), HttpStatus.OK);
    }

}
