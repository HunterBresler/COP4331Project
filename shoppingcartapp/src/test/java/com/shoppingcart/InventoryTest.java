package com.shoppingcart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import com.inventory.Inventory;
import com.inventory.Product;

public class InventoryTest {

    private Inventory inventory;

    @Before
    public void setUp() {
        inventory = new Inventory();
    }

    @Test
    public void testAddProduct() {
        inventory.addProduct(1, "Product1", "Description1", 10, 50.0, 100.0);
        inventory.addProduct(2, "Product2", "Description2", 20, 30.0, 60.0);

        LinkedList<Product> products = inventory.getInventory();

        assertEquals(2, products.size());

        Product product1 = products.get(0);
        assertEquals(1, product1.getProductID());
        assertEquals("Product1", product1.getProductName());

        Product product2 = products.get(1);
        assertEquals(2, product2.getProductID());
        assertEquals("Product2", product2.getProductName());

    }

    @Test
    public void testSaveAndSetInventory() {
        inventory.addProduct(1, "Product1", "Description1", 10, 50.0, 100.0);
        inventory.addProduct(2, "Product2", "Description2", 20, 30.0, 60.0);

        inventory.save();

        Inventory newInventory = new Inventory();
        newInventory.setInventory();

        assertNotNull(newInventory.getInventory());

        LinkedList<Product> products = newInventory.getInventory();
        assertEquals(2, products.size());

        Product product1 = products.get(0);
        assertEquals(1, product1.getProductID());
        assertEquals("Product1", product1.getProductName());


        Product product2 = products.get(1);
        assertEquals(2, product2.getProductID());

    }
}