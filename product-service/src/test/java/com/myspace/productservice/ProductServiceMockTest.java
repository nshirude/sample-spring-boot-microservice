package com.myspace.productservice;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.myspace.productservice.controller.model.Product;
import com.myspace.productservice.dto.ProductRequest;
import com.myspace.productservice.dto.ProductResponseDto;
import com.myspace.productservice.repository.ProductRepository;
import com.myspace.productservice.service.ProductService;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

//The JUnit Runner which causes all the initialization magic with @Mock and @InjectMocks to happen before the tests are run. It does not launch entire spring context
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceMockTest {

	@Mock
	ProductRepository productRepoMock;

	@InjectMocks
	ProductService productService;

	@Test
	public void testFindProductByCode() {
		Optional<Product> mockProduct = Optional.ofNullable(new Product(1, "Pencil", 200));
		Mockito.when(productRepoMock.retrieveProductByCode(Mockito.anyString())).thenReturn(mockProduct);

		final ProductResponseDto expectedResponse = new ProductResponseDto();
		expectedResponse.setProductCode("Pencil");
		expectedResponse.setAvailableQuantity(200);

		assertThat(productService.findProductByCode(Mockito.anyString()), sameBeanAs(expectedResponse));

	}

	@Test
	public void testaddProduct() {
		Product mockProduct = new Product(1, "Pencil", 200);
		Mockito.when(productRepoMock.addProduct(Mockito.any(ProductRequest.class))).thenReturn(mockProduct);

		final Product expectedProduct = new Product();
		expectedProduct.setProductId(1);
		expectedProduct.setProductCode("Pencil");
		expectedProduct.setAvailableQuantity(200);

		assertThat(productService.addProduct(Mockito.any(ProductRequest.class)), sameBeanAs(expectedProduct));

	}

}
