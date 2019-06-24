package com.myspace.productservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspace.productservice.controller.model.Product;
import com.myspace.productservice.dto.ProductRequest;
import com.myspace.productservice.dto.ProductResponseDto;
import com.myspace.productservice.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	ProductRepository productRepo;

	public ProductResponseDto findProductByCode(String code) {
		log.info("findProductByCode :" + code);
		ProductResponseDto productResponse = new ProductResponseDto();
		Optional<Product> product = productRepo.retrieveProductByCode(code);
		if (product.isPresent()) {
			productResponse.setProductCode(product.get().getProductCode());
			productResponse.setAvailableQuantity(product.get().getAvailableQuantity());
		}
		return productResponse;
	}

	public List<Product> findAllProducts() {
		log.info("findAllProducts");
		List<Product> products = productRepo.retrieveAllProducts();
		return products;
	}

	public Product addProduct(ProductRequest prodReq) {
		log.info("addProduct");
		Product product = productRepo.addProduct(prodReq);
		return product;
	}

}
