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

    // Name of the coin (PK)
    @JsonView(Views.ParsedCrypto.class)
    @Id
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
    private Date launchDate;

    // Coin's last transaction date 
    private Date lastTransactionDate;

    // Coin's logo
    private String image;

    // Default constructor
    public Crypto(){

    }

    // Parameterized constructors
    public Crypto(String name, double price, Type Type) {
        this.name = name;
        this.price = price;
        this.Type = Type;
    }

    public Crypto(String name, double price, Type Type, String description, Date initDate, Date lastModDate) {
        this.name = name;
        this.price = price;
        this.Type = Type;
        this.description = description;
        this.launchDate = initDate;
        this.lastTransactionDate = lastModDate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public Date getLaunchDate() {
        return this.launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public Date getLastTransactionDate() {
        return this.lastTransactionDate;
    }

    public void setLastTransactionDate(Date lastModDate) {
        this.lastTransactionDate = lastModDate;
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
        return Objects.equals(name, crypto.name) 
            && Objects.equals(price, crypto.price) 
            && Objects.equals(description, crypto.description) 
            && Objects.equals(Type, crypto.Type) 
            && Objects.equals(launchDate, crypto.launchDate) 
            && Objects.equals(lastTransactionDate, crypto.lastTransactionDate)
            && Objects.equals(image, crypto.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, description, Type, launchDate, lastTransactionDate, image);
    }


    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", price='" + getPrice() + "'" +
            ", description='" + getDescription() + "'" +
            ", Type='" + getType() + "'" +
            ", launchDate='" + getLaunchDate() + "'" +
            ", lastTransactionDate='" + getLastTransactionDate() + "'" +
            "}";
    }
        
}
