package com.inventory;

import java.io.Serializable;

public class Product implements Serializable
{
    private int productID;
    private String productName;
    private String description;
    private int quantity;
    private double invoicePrice;
    private double sellingPrice;

    public Product(int ID, String name, String description, int quantity, double invoicePrice, double sellingPrice)
    {
        this.productID = ID;
        this.productName = name;
        this.description = description;
        this.quantity = quantity;
        this.invoicePrice = invoicePrice;
        this.sellingPrice = sellingPrice;
    }

    // Getter methods
    public int getProductID() { return productID; }
    public String getProductName() { return productName; }
    public String getDescription() { return description; }
    public int getQuantity() { return quantity; }
    public double getInvoicePrice() { return invoicePrice; }
    public double getSellingPrice() { return sellingPrice; }

    // Setter methods
    public void setProductID(int productID) { this.productID = productID; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setDescription(String description) { this.description = description; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setInvoicePrice(double invoicePrice) { this.invoicePrice = invoicePrice; }
    public void setSellingPrice(double sellingPrice) { this.sellingPrice = sellingPrice; }

    // Returns selling price of purchased products and removes them
    public double purchaseProduct(int amountPurchased) throws Exception
    {
        // Check if more product is trying to be sold than what is available
        if (amountPurchased > this.quantity)
        {
            throw new Exception("More products purchased than allowed");
        }
        else
        {
            // Remove product
            this.quantity -= amountPurchased;

            // Return selling price
            return amountPurchased * sellingPrice;
        }
    }
}