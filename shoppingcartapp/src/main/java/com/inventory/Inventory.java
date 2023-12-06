package com.inventory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

public class Inventory implements Serializable
{
    private LinkedList<Product> inventory = new LinkedList<Product>();

    // Getter
    public LinkedList<Product> getInventory() { return this.inventory; }

    // Setter: Uses .txt file
    public void setInventory()
    {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("inventory.txt"))) 
        {
            Inventory newInventory = (Inventory) inputStream.readObject();
            this.inventory = (LinkedList<Product>) newInventory.inventory;
        } 
        catch (IOException | ClassNotFoundException e) 
        {
            e.printStackTrace();
        }
    }

    // Adds product to inventory
    public void addProduct(int ID, String name, String description, int quantity, double invoicePrice, double sellingPrice)
    {
        Product product = new Product(ID, name, description, quantity, invoicePrice, sellingPrice);
        this.inventory.add(product);
    }

    public void save()
    {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("inventory.txt"))) 
        {
            outputStream.writeObject(this);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
