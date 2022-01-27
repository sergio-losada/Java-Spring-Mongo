# Java-Spring-Mongo
Implementation of a REST API service using Java Spring Boot and MongoDB.
Unit tests have been implemented with Mockito and JUnit5 with TestRestTemplate, whereas Testcontainers have been used for the integration tests.

## ENDPOINTS
    GET /crypto
    POST /crypto
    PUT /crypto/{cryptoId}
    DELETE /crypto/{cryptoId}
    GET /crypto/{cryptoId}

## DTO DETAIL
The DTO modeled is a basic representation of cryptocurrencies
It can be applied to any crypto coin and has the following attributes available
- cryptoId
- name
- price (coin's price in US dollars, $USD)
- type (payment, altcoin, NFT, DeFi)
- description
- initDate (Coin's launch date)
- lastModDate (Coin's last transaction date) 
- image (Coin's logo)