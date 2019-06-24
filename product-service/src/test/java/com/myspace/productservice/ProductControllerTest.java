package com.myspace.productservice;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.myspace.productservice.controller.ProductController;
import com.myspace.productservice.controller.model.Product;
import com.myspace.productservice.dto.ProductRequest;
import com.myspace.productservice.dto.ProductResponseDto;
import com.myspace.productservice.service.ProductService;

//launch entire spring context only with specific controller mentioned in controllers attribute
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProductController.class, secure = false)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@Test
	public void fetchProductByCode() throws Exception {

		ProductResponseDto mockProductResponseDto = new ProductResponseDto("Pencil", 200);

		Mockito.when(productService.findProductByCode(Mockito.anyString())).thenReturn(mockProductResponseDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/products/Pencil")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{productCode:Pencil,availableQuantity:200}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	public void createProduct() throws Exception {

		Product mockProduct = new Product(1, "Pencil", 200);

		String exampleProductJson = "{\"productCode\":\"Pencil\",\"availableQuantity\":\"200\",\"productId\":\"1\"}";

		// productService.addProduct to respond back with mockproduct
		Mockito.when(productService.addProduct(Mockito.any(ProductRequest.class))).thenReturn(mockProduct);

		// Send product as body to /api/products
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/products").accept(MediaType.APPLICATION_JSON)
				.content(exampleProductJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/api/products/1", response.getHeader(HttpHeaders.LOCATION));

	}

}
