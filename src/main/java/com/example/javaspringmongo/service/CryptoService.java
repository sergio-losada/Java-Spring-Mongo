package com.example.javaspringmongo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.javaspringmongo.dto.Crypto;
import com.example.javaspringmongo.repository.CryptoRepository;

@Service
public class CryptoService {

    @Autowired
    private CryptoRepository cryptoRepository;

    public ArrayList<Crypto> get() {
        return (ArrayList<Crypto>)this.cryptoRepository.findAll();
    }

    public Crypto post(Crypto crypto) {
        Date date = new Date();  
        // Automatically registered init and lastTransaction dates  
        crypto.setLaunchDate(date);  
        crypto.setLastTransactionDate(date);
        return cryptoRepository.save(crypto);
    }

    public Crypto put(Crypto crypto, String cryptoId) {
        Date date = new Date();
        // initDate must not be altered
        crypto.setLaunchDate(this.getByKey(cryptoId).get().getLaunchDate());
        // register automatically the latest lastModDate 
        crypto.setLastTransactionDate(date);
        return cryptoRepository.save(crypto);
    }

    public void delete(String cryptoId) {
        cryptoRepository.deleteById(cryptoId);
    }

    public Optional<Crypto> getByKey(String cryptoId) {
        return cryptoRepository.findById(cryptoId);
    }
    
}
