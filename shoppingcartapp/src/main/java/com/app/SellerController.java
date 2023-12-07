package com.app;

import java.util.List;

public class SellerController {
    private SellerModel model;

    public SellerController() {
        this.model = new SellerModel("Selling User", "Inventory.txt");
    }

    public List<String> getProducts() {
        return model.getProducts();
    }

    public void addToInventory(String productDetails) {
        // Logic to add a product to the Inventory.txt file
        model.addToInventory(productDetails);
    }

    public void deleteFromInventory(String productDetails) {
        model.deleteFromInventory(productDetails);
    }

}
