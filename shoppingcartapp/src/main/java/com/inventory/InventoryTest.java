package com.inventory;

import java.util.LinkedList;

public class InventoryTest 
{
    public static void main(String[] args) 
    {
        // Create an instance of the Inventory class
        Inventory inventory = new Inventory();

        // Add some sample products to the inventory
        //inventory.addProduct(1, "Product1", "Description1", 10, 20.0, 30.0);
        //inventory.addProduct(2, "Product2", "Description2", 15, 25.0, 35.0);
        //inventory.addProduct(3, "Product3", "Description3", 20, 30.0, 40.0);

        // Test writing to file
        //inventory.save();

        // Clear the inventory for testing reading from file
        //inventory.clear();

        // Test reading from file
        inventory.setInventory();

        LinkedList<Product> list = inventory.getInventory();
        System.out.println(list.getLast().getProductName());

        // Display the updated inventory
        System.out.println("Updated Inventory:");
    }
}
