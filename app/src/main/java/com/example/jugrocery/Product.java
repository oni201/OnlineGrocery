package com.example.jugrocery;

import com.google.firebase.firestore.Exclude;

public class Product {
    private String name;
    private String documentId;
    private String price;
    private String description;

    public Product(String name, String price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Exclude
    public String getId(){
        return documentId;
    }
    public void setId(String documentId){
        this.documentId = documentId;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
