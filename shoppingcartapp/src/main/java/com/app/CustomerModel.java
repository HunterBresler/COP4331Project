package com.app;

import com.inventory.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The CustomerModel class represents the data model in the MVC pattern for the customer section of the application.
 * It manages the product catalog and shopping cart functionalities.
 */
public class CustomerModel {
    private final List<Product> products = new ArrayList<>();
    private final Map<Integer, Product> productCatalog = new HashMap<>();
    private final Cart cart;

    /**
     * Constructs a new CustomerModel, initializing the product catalog and cart.
     */
    public CustomerModel() {
        initializeProductCatalog();
        cart = new Cart(productCatalog);            
    }

    /**
     * Retrieves the product catalog.
     *
     * @return A Map representing the product catalog, with product ID as key and Product object as value.
     */
    public Map<Integer, Product> getProductCatalog() {
        return productCatalog;
    }

    /**
     * Initializes the product catalog with a predefined set of products.
     */
    private void initializeProductCatalog() {
        for (int i = 1; i <= 10; i++) {
            Product product = new Product(i, "Product " + i, "Description", 100, 5.0, i * 10.0);
            products.add(product);
            productCatalog.put(i, product);
        }
    }

    /**
     * Retrieves the list of products.
     *
     * @return A List of Product objects.
     */
    public List<Product> getProducts() {
        return products;
    }
    
    /**
     * Retrieves a specific product by its ID.
     *
     * @param productId The ID of the product to retrieve.
     * @return The Product object if found, or null if not found.
     */
    public Product getProduct(int productId) {
        return productCatalog.get(productId);
    }

    /**
     * Retrieves the shopping cart associated with the customer.
     *
     * @return The Cart object representing the customer's shopping cart.
     */
    public Cart getCart() {
        return cart;
    }

    // Other necessary methods...
}
