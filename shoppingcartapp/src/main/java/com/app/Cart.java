package com.app;

import com.inventory.Product;
import java.util.HashMap;
import java.util.Map;

class Cart {
    private final Map<Integer, Integer> productQuantities = new HashMap<>();
    private Map<Integer, Product> productCatalog; // Assuming a product catalog is available

    public Cart(Map<Integer, Product> productCatalog) {
        this.productCatalog = productCatalog;
    }

    public void addOrUpdateProduct(int productId, int quantity) {
        productQuantities.put(productId, productQuantities.getOrDefault(productId, 0) + quantity);
    }

    public void updateProductQuantity(int productId, int quantity) {
        if (quantity <= 0) {
            productQuantities.remove(productId);
        } else {
            productQuantities.put(productId, quantity);
        }
    }

    public void removeProduct(int productId) {
        productQuantities.remove(productId);
    }

    public Map<Product, Integer> getCartContents() {
        Map<Product, Integer> contents = new HashMap<>();
        productQuantities.forEach((id, qty) -> {
            Product product = productCatalog.get(id);
            if (product != null) {
                contents.put(product, qty);
            }
        });
        return contents;
    }

    public double calculateTotal() {
    return productQuantities.entrySet().stream()
            .mapToDouble(entry -> {
                Product product = productCatalog.get(entry.getKey());
                return product != null ? product.getSellingPrice() * entry.getValue() : 0;
            })
            .sum();
    }
}
    
