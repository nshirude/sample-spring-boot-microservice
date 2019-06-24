package com.myspace.productservice.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.myspace.productservice.controller.model.Product;
import com.myspace.productservice.dto.ProductRequest;

@Repository
public class ProductRepository {
	
	 // Some dummy data
	// Actually this should interact with some database (using data JPA repository or plain JDBC) to get all the data
	
	private static List<Product> products = new ArrayList<>();
	
	static {
		//Initialize some dummy Data
		Product product1 = new Product(1,"Pencil",200);		
		Product product2 = new Product(2,"Pen",100);
		Product product3 = new Product(3,"notepad",150);
		products.add(product1);
		products.add(product2);
		products.add(product3);
	}
	
	public List<Product> retrieveAllProducts() {
		return products;
	}
	
	public Optional<Product> retrieveProductByCode(String productCode) {
		for (Product product : products) {
			if (product.getProductCode().equals(productCode)) {
				return Optional.ofNullable(product);
			}
		}
		return Optional.empty();
	}
	
	public Product addProduct(ProductRequest prodReq) {
		Product product = new Product(4,prodReq.getProductCode(),prodReq.getAvailableQuantity());	
		return product;
	}

}
