package com.shoppingcart;

import com.app.*;
import com.inventory.Product;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CartTest {

    private Cart cart;
    private Map<Integer, Product> productCatalog;

    @Before
    public void setUp() {
        productCatalog = new HashMap<>();
        productCatalog.put(1, new Product(001, "product1", "desc", 1, 10.0, 10.0));
        productCatalog.put(2, new Product(002, "product2", "desc", 1, 20.0, 20.0));

        cart = new Cart(productCatalog);
    }

    @Test
    public void testAddOrUpdateProduct() {
        cart.addOrUpdateProduct(1, 2);
        cart.addOrUpdateProduct(2, 3);

        assertEquals(2, cart.getCartContents().get(productCatalog.get(1)).intValue());
        assertEquals(3, cart.getCartContents().get(productCatalog.get(2)).intValue());
    }

    @Test
    public void testUpdateProductQuantity() {
        cart.addOrUpdateProduct(1, 2);
        cart.updateProductQuantity(1, 5);

        assertEquals(5, cart.getCartContents().get(productCatalog.get(1)).intValue());
    }

    @Test
    public void testRemoveProduct() {
        cart.addOrUpdateProduct(1, 2);
        cart.removeProduct(1);

        assertNull(cart.getCartContents().get(productCatalog.get(1)));
    }

    @Test
    public void testGetCartContents() {
        cart.addOrUpdateProduct(1, 2);
        cart.addOrUpdateProduct(2, 3);

        Map<Product, Integer> expectedContents = new HashMap<>();
        expectedContents.put(productCatalog.get(1), 2);
        expectedContents.put(productCatalog.get(2), 3);

        assertEquals(expectedContents, cart.getCartContents());
    }

    @Test
    public void testCalculateTotal() {
        cart.addOrUpdateProduct(1, 2);
        cart.addOrUpdateProduct(2, 3);

        // Product1 sells for 10.0 and Product2 for 20.0
        double expectedTotal = 2 * 10.0 + 3 * 20.0;

        assertEquals(expectedTotal, cart.calculateTotal(), 0.001); 
    }
}