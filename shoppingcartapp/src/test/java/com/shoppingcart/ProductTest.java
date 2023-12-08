package com.shoppingcart;

import org.junit.Before;
import org.junit.Test;

import com.inventory.Product;

import static org.junit.Assert.assertEquals;

public class ProductTest {

    private Product product;

    @Before
    public void setUp() {
        product = new Product(1, "TestProduct", "Test Description", 10, 50.0, 100.0);
    }

    @Test
    public void testGetters() {
        assertEquals(1, product.getProductID());
        assertEquals("TestProduct", product.getProductName());
        assertEquals("Test Description", product.getDescription());
        assertEquals(10, product.getQuantity());
        assertEquals(50.0, product.getInvoicePrice(), 0.001);
        assertEquals(100.0, product.getSellingPrice(), 0.001);
    }

    @Test
    public void testSetters() {
        product.setProductID(2);
        product.setProductName("UpdatedProduct");
        product.setQuantity(20);
        product.setInvoicePrice(60.0);

        assertEquals(2, product.getProductID());
        assertEquals("UpdatedProduct", product.getProductName());
        assertEquals(20, product.getQuantity());
        assertEquals(60.0, product.getInvoicePrice(), 0.001);
    }

    @Test
    public void testPurchaseProduct() throws Exception {
        double totalPrice = product.purchaseProduct(5);

        assertEquals(5, product.getQuantity());
        assertEquals(500.0, totalPrice, 0.001);
    }

    @Test(expected = Exception.class)
    public void testPurchaseProductException() throws Exception {
        // should throw an exception
        product.purchaseProduct(15);
    }
}
