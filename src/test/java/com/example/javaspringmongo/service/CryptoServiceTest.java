package com.example.javaspringmongo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import com.example.javaspringmongo.dto.Crypto;
import com.example.javaspringmongo.mock.MockingCrypto;
import com.example.javaspringmongo.util.Type;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class CryptoServiceTest {
    
	@MockBean
	private CryptoService cryptoService;

    @BeforeEach
	public void init() {
		MockingCrypto.mockingCrypto(cryptoService);
    }

	@Test
	@Order(1)
	public void test01_get() {
		// Testing request
		ArrayList<Crypto> response = this.cryptoService.get();

		// Testing response
		assertEquals(response.size(), 2, "Response size must be 2");

		assertEquals(response.get(0).getName(), "ETH", "Crypto ids must be equals");
		assertEquals(response.get(0).getPrice(), 3000.0, "Crypto price must be equals");
		assertEquals(response.get(0).getType(), Type.payment, "Crypto type must still be equals");

		assertEquals(response.get(1).getName(), "MANA", "Crypto ids must be equals");
		assertEquals(response.get(1).getPrice(), 1.90, "Crypto price must be equals");
		assertEquals(response.get(1).getType(), Type.NFT, "Crypto type must still be equals");

		// Mockito verification that the method was only invoked once
		Mockito.verify(cryptoService).get();
	}

	@Test
	@Order(1)
	public void test02_post() {
		// Testing request (no parameterized constructor needed as the object to insert is mocked)
		Crypto api = new Crypto();
		Crypto response = this.cryptoService.post(api);

		// Testing response
		assertEquals(response.getName(), "AVAX", "Crypto ids must be equals");
		assertEquals(response.getPrice(), 61.2, "Crypto price must be equals");
		assertEquals(response.getType(), Type.DeFi, "Crypto type must still be equals");
		
		// Mockito verification that the method was only invoked once
		Mockito.verify(cryptoService).post(api);
	}

    @Test
	@Order(1)
	public void test03_put() {
		// Testing request (no parameterized constructor needed as the object to update is mocked)
		Crypto crypto = new Crypto();
		String name = new String();
		Crypto response = this.cryptoService.put(crypto, name);

		// Testing response
		assertEquals(response.getName(), "DOT", "Crypto ids must be equals");
		assertEquals(response.getPrice(), 32.1, "Crypto price must be equals");
		assertEquals(response.getType(), Type.altcoin, "Crypto type must still be equals");

		// Mockito verification that the method was only invoked once
		Mockito.verify(cryptoService).put(crypto, name);
	}

    @Test
	@Order(1)
	public void test04_delete() {
		// Testing request (no real API name needed as the object to delete is mocked)
		this.cryptoService.delete(new String());

		// Mockito verification that the method was only invoked once (No testing response as the method is void)
		Mockito.verify(cryptoService).delete(new String());
	}

    @Test
	@Order(1)
	public void test05_getByKey() {
		// Testing request (no real API name needed as the object to retrieve is mocked)
		Optional<Crypto> response = this.cryptoService.getByKey(new String());

		// Testing response
		assertTrue(response.isPresent() && !response.isEmpty(), "Response entity must be present");
		assertEquals(response.get().getName(), "ENJ", "Crypto ids must be equals");
		assertEquals(response.get().getPrice(), 2.07, "Crypto price must be equals");
		assertEquals(response.get().getType(), Type.NFT, "Crypto type must still be equals");

		// Mockito verification that the method was only invoked once
		Mockito.verify(cryptoService).getByKey(new String());
    }

}
