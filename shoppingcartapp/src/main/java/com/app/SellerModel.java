package com.app;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.inventory.Product;

import com.app.SellerObserver;

public class SellerModel {
    private String sellerName;
    private List<String> products;
    private List<SellerObserver> observers = new ArrayList<>();
    private String inventoryFileName;
    
    public List<String> getProducts() {
        return products;
    }
    
    public void addOrUpdateProduct(String productDetails, boolean isUpdate) {
        // Split the details
        String[] details = productDetails.split(",");
        // Assuming details format: id,name,quantity,invoicePrice,sellingPrice
        String productId = details[0].trim();
        String productName = details[1].trim();
        int quantity = Integer.parseInt(details[2].trim());
        double invoicePrice = Double.parseDouble(details[3].trim());
        double sellingPrice = Double.parseDouble(details[4].trim());

        // Check if updating or adding new product
        if (isUpdate) {
            updateProduct(productId, productName, quantity, invoicePrice, sellingPrice);
        } else {
            addNewProduct(productId, productName, quantity, invoicePrice, sellingPrice);
        }
    }
    
    private void updateProduct(String productId, String productName, int quantity, double invoicePrice, double sellingPrice) {
        // Logic to update the product in inventory
        // ...
        System.out.println("Updated product: " + productId);
    }

    private void addNewProduct(String productId, String productName, int quantity, double invoicePrice, double sellingPrice) {
        // Logic to add the new product to inventory
        // ...
        System.out.println("Added new product: " + productId);
    }

    public SellerModel(String sellerName, String inventoryFileName) {
    this.sellerName = sellerName;
    this.products = new ArrayList<>();
    this.inventoryFileName = inventoryFileName;
    System.out.println("SellerModel: Initialized with sellerName: " + sellerName);
    loadInventory();
}

private void loadInventory() {
    try (BufferedReader reader = new BufferedReader(new FileReader(inventoryFileName))) {
        String line;
        while ((line = reader.readLine()) != null) {
            products.add(line);
            System.out.println("SellerModel: Loaded product: " + line);
        }
    } catch (IOException e) {
        System.out.println("Error loading inventory: " + e.getMessage());
    }
}

private void saveInventory() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(inventoryFileName))) {
        for (String product : products) {
            writer.write(product);
            writer.newLine();
        }
        System.out.println("SellerModel: Inventory saved");
    } catch (IOException e) {
        System.out.println("Error saving inventory: " + e.getMessage());
    }
}
    
    public void addToInventory(String productDetails) {
        products.add(productDetails);
        notifyObservers(productDetails);
        saveInventory();
    }

    public void deleteFromInventory(String productDetails) {
        products.removeIf(product -> product.contains(productDetails));
        notifyObservers(productDetails);
        saveInventory();
    }
    
     public List<Product> getInventory() {
        // Logic to return the list of Product objects representing the inventory
        // For example, convert the 'products' string list to Product objects
        return convertStringListToProductList(products);
    }

    private List<Product> convertStringListToProductList(List<String> productsStringList) {
    List<Product> productList = new ArrayList<>();
    for (String productDetails : productsStringList) {
        String[] details = productDetails.split(",");
        int productId = Integer.parseInt(details[0].trim());
        String productName = details[1].trim();
        String description = details[2].trim();
        int quantity = Integer.parseInt(details[3].trim());
        double invoicePrice = Double.parseDouble(details[4].trim());
        double sellingPrice = Double.parseDouble(details[5].trim());

        Product product = new Product(productId, productName, description, quantity, invoicePrice, sellingPrice);
        productList.add(product);
    }
    return productList;
}
   

    public void addObserver(SellerObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(SellerObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String productDetails) {
        for (SellerObserver observer : observers) {
            observer.notify(productDetails);
        }
    }
}
