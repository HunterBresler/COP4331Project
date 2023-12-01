package com.shoppingcart;

import java.util.*;

public class ShoppingCart {
    private Map<Product, Integer> products; // Product and Quantity
    
    public ShoppingCart() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        this.products.put(product, this.products.getOrDefault(product, 0) + quantity);
    }

    public double calculateTotalAmount() {
        return this.products.entrySet().stream().mapToDouble(e -> e.getKey().getSellingPrice() * e.getValue()).sum();
    }

    public double getTotal() {
        return calculateTotalAmount();
    }
    
    // Other methods to remove and update products
}