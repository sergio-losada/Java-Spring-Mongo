package com.example.javaspringmongo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonView;
import com.example.javaspringmongo.util.Type;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("cryptocurrency")
public class Crypto implements Serializable {

    // Id of the crypto (PK)
    @JsonView(Views.ParsedCrypto.class)
    @Id
    private String cryptoId;

    // Name of the coin
    @JsonView(Views.ParsedCrypto.class)
    private String name;
    
    // Coin's price in USD
    @JsonView(Views.ParsedCrypto.class)
    private double price;

    // Type of cryptocurrency
    @JsonView(Views.ParsedCrypto.class)
    private Type Type;

    // Description of the coin
    private String description;

    // Coin's launch date
    private Date initDate;

    // Coin's last transaction date 
    private Date lastModDate;

    // Coin's logo
    private String image;

    // Default constructor
    public Crypto(){

    }

    // Parameterized constructors
    public Crypto(String cryptoId, String name, Type Type) {
        this.cryptoId = cryptoId;
        this.name = name;
        this.Type = Type;
    }

    public Crypto(String cryptoId, String name, Type Type, String description, Date initDate, Date lastModDate) {
        this.cryptoId = cryptoId;
        this.name = name;
        this.Type = Type;
        this.description = description;
        this.initDate = initDate;
        this.lastModDate = lastModDate;
    }
    
    public String getCryptoId() {
        return this.cryptoId;
    }

    public void setCryptoId(String cryptoId) {
        this.cryptoId = cryptoId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return this.Type;
    }

    public void setType(Type Type) {
        this.Type = Type;
    }

    public Date getInitDate() {
        return this.initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Date getLastModDate() {
        return this.lastModDate;
    }

    public void setLastModDate(Date lastModDate) {
        this.lastModDate = lastModDate;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Crypto)) {
            return false;
        }
        Crypto crypto = (Crypto) o;
        return Objects.equals(cryptoId, crypto.cryptoId) 
            && Objects.equals(name, crypto.name) 
            && Objects.equals(description, crypto.description) 
            && Objects.equals(Type, crypto.Type) 
            && Objects.equals(initDate, crypto.initDate) 
            && Objects.equals(lastModDate, crypto.lastModDate)
            && Objects.equals(image, crypto.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cryptoId, name, description, Type, initDate, lastModDate, image);
    }


    @Override
    public String toString() {
        return "{" +
            " cryptoId;='" + getCryptoId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", Type='" + getType() + "'" +
            ", initDate='" + getInitDate() + "'" +
            ", lastModDate='" + getLastModDate() + "'" +
            "}";
    }
        
}
