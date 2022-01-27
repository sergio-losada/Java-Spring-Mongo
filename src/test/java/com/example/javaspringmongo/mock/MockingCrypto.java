package com.example.javaspringmongo.mock;

import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;

import org.mockito.Mockito;
import com.example.javaspringmongo.dto.Crypto;
import com.example.javaspringmongo.service.CryptoService;
import com.example.javaspringmongo.util.Type;

public class MockingCrypto {

    public static void mockingCrypto(CryptoService cryptoService) {
        get(cryptoService);
        post(cryptoService);
        put(cryptoService);
        delete(cryptoService);
        getByKey(cryptoService);
    }

    public static void get(CryptoService cryptoService) {
        // Create 2 Crypto objects just with name, price and type
        Crypto ethereum = new Crypto("ETH", 3000.0, Type.payment);
        Crypto decentraland = new Crypto("MANA", 1.90, Type.NFT);

        // Add the Api objects to the returned list
        ArrayList<Crypto> list = new ArrayList<>(Arrays.asList(ethereum, decentraland));

        // Return the list whenever GET is invoked
        Mockito.when(cryptoService.get()).thenReturn(list);
    }

    public static void post(CryptoService cryptoService) {
        // Create a Crypto object with name, price and type
        Crypto avalanche = new Crypto("AVAX", 61.2, Type.DeFi);

        // Return the Api object whenever POST is invoked
        Mockito.when(cryptoService.post(Mockito.any(Crypto.class))).thenReturn(avalanche);
    }

    public static void put(CryptoService cryptoService) {
        // Create a Crypto object with name, price and type
        Crypto polkadot = new Crypto("DOT", 32.1, Type.altcoin); 
        
        // Return the Api object whenever PUT is invoked
        Mockito.when(cryptoService.put(Mockito.any(Crypto.class), Mockito.anyString())).thenReturn(polkadot);
    }

    public static void delete(CryptoService cryptoService) {
        Mockito.doAnswer(invocation -> {
            return invocation;
        }).when(cryptoService).delete(Mockito.anyString());
    }

    public static void getByKey(CryptoService cryptoService) {
        // Create a Crypto Object with name, price and type
        Crypto enjin = new Crypto("ENJ", 2.07, Type.NFT); 

        // Return the Api object whenever GETBYKEY is invoked
        Mockito.when(cryptoService.getByKey(Mockito.anyString())).thenReturn(Optional.of(enjin));
    }

}
