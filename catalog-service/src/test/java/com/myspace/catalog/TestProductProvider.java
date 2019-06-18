package com.myspace.catalog;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.myspace.catalog.dto.ProductInventoryResponse;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.junit.Assert.assertThat;

public class TestProductProvider {
	
	@Rule
    public PactProviderRuleMk2 provider = new PactProviderRuleMk2("ProductServiceProvider", "localhost", 8089, this);
	
	@Pact(consumer = "CatalogServiceClient")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap();
        headers.put("Content-Type", "application/json");

        DslPart productResult = new PactDslJsonBody()
                .stringType("productCode","Pencil")               
                .integerType("availableQuantity",200)
                .asBody();

        return builder
                .given("Product Code")
                .uponReceiving("Product availableQuantity")
                .path("/api/products/Pencil")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body(productResult).toPact();

    }
	
	 	@Test
	    @PactVerification()
	 	//@PactTestFor(pactMethod = "createPact")
	    public void runTest() throws IOException {
	 		 System.setProperty("pact.rootDir","../pacts");
		 	String productCode = "Pencil";
	        final RestTemplate call = new RestTemplate();
	        final ProductInventoryResponse expectedResponse = new ProductInventoryResponse();
	        expectedResponse.setProductCode("Pencil");
	        expectedResponse.setAvailableQuantity(200);
	        final ResponseEntity<ProductInventoryResponse> itemResponseEntity = call.getForEntity(provider.getConfig().url()+"/api/products/{code}", ProductInventoryResponse.class, productCode);
	        assertThat(itemResponseEntity.getBody(), sameBeanAs(expectedResponse));

	    }

}
