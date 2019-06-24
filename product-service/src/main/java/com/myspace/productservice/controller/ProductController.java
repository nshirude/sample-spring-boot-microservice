package com.myspace.productservice.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myspace.productservice.controller.model.Product;
import com.myspace.productservice.dto.ProductRequest;
import com.myspace.productservice.dto.ProductResponseDto;
import com.myspace.productservice.exception.ValidationException;
import com.myspace.productservice.service.ProductService;
import com.myspace.productservice.util.Constants;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/{code}")
	public ResponseEntity<ProductResponseDto> productByCode(@PathVariable String code) {
		log.info("Finding product by code :" + code);
		return new ResponseEntity<>(productService.findProductByCode(code), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<Product>> findAllProducts() {
		log.info("Find All Products");
		return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<Void> addProduct(@Valid @RequestBody ProductRequest request, Errors errors) {
		log.info("Adding product with product name and code");
		if (errors.hasErrors()) {
			throw new ValidationException(Constants.FAILURE_CODE, errors.getFieldError().getDefaultMessage());
		}

		Product product = productService.addProduct(request);
		if (null == product) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(product.getProductId()).toUri();

		return ResponseEntity.created(location).build();
		// return new ResponseEntity<>(product, HttpStatus.CREATED);
	}

}
