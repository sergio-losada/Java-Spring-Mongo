package com.example.javaspringmongo.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.javaspringmongo.dto.Crypto;
import com.example.javaspringmongo.util.Type;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
@RunWith(SpringRunner.class)
public class CryptoControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

    private static String serverPort = "8080";
	private final static String BASE_URL = "http://localhost:" + serverPort;
	private final static String ABSOLUTE_URI = BASE_URL + "/crypto";
	
	@Test
	@Order(1)
	public void test01_post() {
		// Initialization: Crypto object to insert
		Crypto bitcoin = new Crypto("BTC", 60000.0, Type.payment);
		// POST - TestRestTemplate
		ResponseEntity<Crypto> response = this.testRestTemplate.postForEntity(ABSOLUTE_URI, bitcoin, Crypto.class);
		// Testing response
		assertNotNull(response, "Response must not be null");
		assertEquals(response.getStatusCode(), HttpStatus.CREATED, "HTTP status code must be 201 CREATED");
		// Testing response body
		assertNotNull(response.getBody(), "Response body must not be null");
		assertTrue(response.getBody() instanceof Crypto, "Response body must be a Crypto object");
		assertEquals(response.getBody().getName(), "BTC", "Crypto names must be equals");
		assertEquals(response.getBody().getPrice(), 60000.0, "Crypto price must be equals");
		assertEquals(response.getBody().getType(), Type.payment, "Crypto type must be equals");

		// Initialization: another Crypto object to insert
		Crypto cardano = new Crypto("ADA", 1.32, Type.altcoin);
		// POST - TestRestTemplate
		response = this.testRestTemplate.postForEntity(ABSOLUTE_URI, cardano, Crypto.class);
		// Testing response
		assertNotNull(response, "Response must not be null");
		assertEquals(response.getStatusCode(), HttpStatus.CREATED, "HTTP status code must be 201 CREATED");
		// Testing response body
		assertNotNull(response.getBody(), "Response body must not be null");
		assertTrue(response.getBody() instanceof Crypto, "Response body must be a Crypto object");
		assertEquals(response.getBody().getName(), "ADA", "Crypto names must be equals");
		assertEquals(response.getBody().getPrice(), 1.32, "Crypto price must be equals");
		assertEquals(response.getBody().getType(), Type.altcoin, "Crypto type must be equals");
	}

	@Test
	@Order(2)
	public void test02_put() {
		// Initialization: Crypto object to update
		Crypto bitcoin = new Crypto("BTC", 56000.0, Type.payment);
		// PUT - RestTemplate
		HttpEntity<Crypto> requestEntity = new HttpEntity<>(bitcoin, new HttpHeaders());
		ResponseEntity<Crypto> response = new RestTemplate().exchange(ABSOLUTE_URI + "/BTC", HttpMethod.PUT, requestEntity, Crypto.class);
		// Testing response
		assertNotNull(response, "Response must not be null");
		assertEquals(response.getStatusCode(), HttpStatus.OK, "HTTP status code must be 200 OK");
		// Testing response body
		assertNotNull(response.getBody(), "Response body must not be null");
		assertTrue(response.getBody() instanceof Crypto, "Response body must be an Api object");
		assertEquals(response.getBody().getName(), "BTC", "Crypto ids must be equals");
		assertNotEquals(response.getBody().getPrice(), 60000.0, "Crypto price must not be 60K anymore");
		assertEquals(response.getBody().getPrice(), 56000.0, "Crypto price must be 56K now");
		assertEquals(response.getBody().getType(), Type.payment, "Crypto type must still be equals");
	}

	@Test
	@Order(3)
	public void test03_get() {
		// GET - TestRestTemplate
		ResponseEntity<Crypto[]> response = this.testRestTemplate.getForEntity(ABSOLUTE_URI, Crypto[].class);
		// Testing response
		assertNotNull(response, "Response must not be null");
		assertEquals(response.getStatusCode(), HttpStatus.OK, "HTTP status code must be 200 OK");
		// Testing response body
		assertNotNull(response.getBody(), "Response body must not be null");
		assertTrue(response.getBody().length >= 1, "Response body must have at least 1 object");
		for(Crypto crypto: response.getBody()){
			assertTrue(crypto instanceof Crypto, "Response body must be a list of Crypto objects");
			if(crypto.getName().equals("BTC")){
				assertEquals(crypto.getPrice(), 56000.0, "Crypto price must be equals");
				assertEquals(crypto.getType(), Type.payment, "Crypto type must be equals");
			}
			else if(crypto.getName().equals("ADA")){
				assertEquals(crypto.getPrice(), 1.32, "Crypto price must be equals");
				assertEquals(crypto.getType(), Type.altcoin, "Crypto type must be equals");
			}
		}
	}

	@Test
	@Order(4)
	public void test04_getByKey() {
		// GETBYKEY - RestTemplate
		ResponseEntity<Crypto> response = this.testRestTemplate.getForEntity(ABSOLUTE_URI + "/ADA", Crypto.class);
		// Testing response
		assertNotNull(response, "Response entity must be not null");
		assertEquals(response.getStatusCode(), HttpStatus.OK, "Status code must be 200 OK");
		// Testing response body
		assertNotNull(response.getBody(), "Response body must not be null");
		assertTrue(response.getBody() instanceof Crypto, "Response body must be an Api object");
		assertEquals(response.getBody().getName(), "ADA", "Crypto names must be equals");
		assertEquals(response.getBody().getPrice(), 1.32, "Crypto price must be equals");
		assertEquals(response.getBody().getType(), Type.altcoin, "Crypto type must be equals");
	}

	@Test
	@Order(5)
	public void test5_delete() {
		// DELETE - RestTemplate
		ResponseEntity<Void> response = new RestTemplate().exchange(ABSOLUTE_URI + "/BTC", HttpMethod.DELETE, null, Void.class);
		// Testing response
		assertNotNull(response, "Response must not be null");
		assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT, "HTTP status code must be 204 NO CONTENT");
		// Testing response body
		assertNull(response.getBody(), "Response body must be null");

		response = new RestTemplate().exchange(ABSOLUTE_URI + "/ADA", HttpMethod.DELETE, null, Void.class);
		// Testing response
		assertNotNull(response, "Response must not be null");
		assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT, "HTTP status code must be 204 NO CONTENT");
		// Testing response body
		assertNull(response.getBody(), "Response body must be null");
	}

}
