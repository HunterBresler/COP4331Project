package com.app;

import java.util.List;
import com.inventory.Product;

public class SellerController {
    private List<Product> inventory; // List to store inventory items
    private SellerView view; // Instance of the SellerView
    private SellerModel model; // Instance of SellerModel

    public SellerController(SellerModel model) {
        this.model = model;
        this.inventory = model.getInventory();
        System.out.println("SellerController: Initialized with model: " + model);
    }

    public List<String> getProducts() {
        return model.getProducts();
    }

    public void addToInventory(String productDetails) {
        // Logic to add a product to the Inventory.txt file
        model.addToInventory(productDetails);
        
    }
    
     public void addOrUpdateProduct(String productDetails, boolean isUpdate) {
        model.addOrUpdateProduct(productDetails, isUpdate);
        calculateProfit(); // Recalculate profit if inventory changes
    }


    public void deleteFromInventory(String productDetails) {
        model.deleteFromInventory(productDetails);
    }

    public void calculateProfit() {
    if (inventory == null || inventory.isEmpty()) {
        System.out.println("SellerController: Inventory is null or empty");
        return;
    }

    double cost = 0.0;
    double revenue = 0.0;
    for (Product product : inventory) {
        cost += product.getInvoicePrice() * product.getQuantity();
        revenue += product.getSellingPrice() * product.getSoldQuantity();
    }

    double profit = revenue - cost;
    view.updateProfitDisplay(cost, revenue, profit);
}
    
    public void setView(SellerView view) {
        System.out.println("SellerView has been set in SellerController.");
        this.view = view;
        // Additional setup if necessary...
        // Register the view as an observer to the model
        model.addObserver(view);
    }
}
