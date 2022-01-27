package com.example.javaspringmongo.controller;

import java.util.ArrayList;

import com.example.javaspringmongo.dto.Crypto;
import com.example.javaspringmongo.dto.Views;
import com.example.javaspringmongo.service.CryptoService;
import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crypto")
public class CryptoController {
    
    @Autowired
    private CryptoService cryptoService;

    /**
     * GET OPERATION 
     * @return all Cryptos (JSON Array), 200 OK
     */
    @GetMapping(produces = "application/json")
    @JsonView(Views.ParsedCrypto.class)
    public ResponseEntity<ArrayList<Crypto>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(cryptoService.get());
    }

    /**
     * POST OPERATION
     * @param Crypto request body (Crypto to insert)
     * @return inserted Crypto (JSON), 201 CREATED or 409 CONFLICT
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Crypto> post(@RequestBody Crypto crypto){ 
        if(cryptoService.getByKey(crypto.getName()).isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); 
        else {
            this.cryptoService.post(crypto);
            return ResponseEntity.status(HttpStatus.CREATED).body(crypto);
        } 
    }

    /**
     * PUT OPERATION
     * @param Crypto request body (Crypto to update)
     * @param name path variable (name of the Crypto to update)
     * @return updated Crypto (JSON), 200 OK or 404 NOT FOUND
     */
    @PutMapping(value = "{name}", consumes = "application/json")
    public ResponseEntity<Crypto> put(@RequestBody Crypto crypto, @PathVariable("name") String name){
        if(!cryptoService.getByKey(name).isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
        else {
            this.cryptoService.put(crypto, name);
            return ResponseEntity.status(HttpStatus.OK).body(crypto); 
        }
    }

    /**
     * DELETE OPERATION
     * @param name path variable (name of the Crypto to delete)
     * @return empty response body, 204 NO CONTENT or 404 NOT FOUND
     */
    @DeleteMapping("{name}")
    public ResponseEntity<Void> delete(@PathVariable("name") String name){
        if(!cryptoService.getByKey(name).isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else {
            this.cryptoService.delete(name);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    /**
     * GET BY KEY OPERATION
     * @param name path variable (name of the Crypto to retrieved)
     * @return Crypto that matchs the key (JSON), 200 OK or 404 NOT FOUND 
     */
    @GetMapping(value = "{name}", produces = "application/json")
    public ResponseEntity<Crypto> getByKey(@PathVariable("name") String name){
        if(!cryptoService.getByKey(name).isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);    
        else 
            return ResponseEntity.status(HttpStatus.OK).body(cryptoService.getByKey(name).get());
    }

}
