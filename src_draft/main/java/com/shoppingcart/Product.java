package com.shoppingcart;

public class Product {
    private int id;
    private String name;
    private String type;
    private double sellingPrice;
    private double invoicePrice;
    private int quantityAvailable;

    // Constructor
    public Product(int id, String name, String type, double sellingPrice, double invoicePrice, int quantityAvailable) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.sellingPrice = sellingPrice;
        this.invoicePrice = invoicePrice;
        this.quantityAvailable = quantityAvailable;
    }
    
    // Getter for sellingPrice
    public double getSellingPrice() {
        return sellingPrice;
    }
    
    // Additional Getters and Setters
    // ... (Implement for other instance variables)
}