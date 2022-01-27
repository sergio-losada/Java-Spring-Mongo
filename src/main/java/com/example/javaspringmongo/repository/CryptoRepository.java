package com.example.javaspringmongo.repository;

import com.example.javaspringmongo.dto.Crypto;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoRepository extends MongoRepository<Crypto, String> {
    
}
