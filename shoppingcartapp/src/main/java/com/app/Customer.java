package com.app;

import com.inventory.Product;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer extends JFrame {
    private final List<Product> products = new ArrayList<>();
    private final Map<Integer, Product> productCatalog = new HashMap<>();
    private final Cart cart;
    private final JPanel productsPanel = new JPanel();

    public Customer() {
        initializeProductCatalog();
        cart = new Cart(productCatalog);  // Initialize Cart with product catalog
        initializeUI();
    }

    private void initializeProductCatalog() {
        // Initialize product catalog
        for (int i = 1; i <= 10; i++) {
            Product product = new Product(i, "Product " + i, "Description", 100, 5.0, i * 10.0);
            products.add(product);
            productCatalog.put(i, product);  // Add product to the catalog
        }
    }

    private void initializeUI() {
        setTitle("Customer Dashboard");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        productsPanel.setLayout(new GridLayout(0, 2, 10, 10));
        for (Product product : products) {
            JButton productButton = new JButton(product.getProductName() + " - $" + product.getSellingPrice());
            productButton.addActionListener(e -> {
                cart.addOrUpdateProduct(product.getProductID(), 1);  // Use product ID
                updateCartView();
            });
            productsPanel.add(productButton);
        }

        add(productsPanel, BorderLayout.CENTER);

        JButton cartButton = new JButton("View Cart");
        cartButton.addActionListener(e -> displayCartContents());
        add(cartButton, BorderLayout.NORTH);

        setVisible(true);
    }

    private void updateCartView() {
        // Update the view to reflect changes in the cart
        // Implement any necessary logic here
    }

    private void displayCartContents() {
    JFrame cartFrame = new JFrame("Shopping Cart");
    cartFrame.setSize(400, 300);
    cartFrame.setLayout(new BorderLayout());

    JPanel cartItemsPanel = new JPanel();
    cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
    JScrollPane scrollPane = new JScrollPane(cartItemsPanel);

    JButton checkoutButton = new JButton("Checkout - Total: $" + cart.calculateTotal());
    checkoutButton.addActionListener(e -> JOptionPane.showMessageDialog(cartFrame, "Checkout completed. Total cost: $" + cart.calculateTotal()));

    for (Map.Entry<Product, Integer> entry : cart.getCartContents().entrySet()) {
        Product product = entry.getKey();
        int quantity = entry.getValue();

        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        itemPanel.add(new JLabel(product.getProductName() + " - $" + product.getSellingPrice()));

        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(quantity, 0, 100, 1));
        quantitySpinner.addChangeListener(e -> {
            int newQuantity = (Integer) quantitySpinner.getValue();
            cart.updateProductQuantity(product.getProductID(), newQuantity);
            checkoutButton.setText("Checkout - Total: $" + cart.calculateTotal()); // Update the checkout total
        });
        itemPanel.add(quantitySpinner);

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> {
            cart.removeProduct(product.getProductID());
            cartItemsPanel.remove(itemPanel);
            cartItemsPanel.revalidate();
            cartItemsPanel.repaint();
            checkoutButton.setText("Checkout - Total: $" + cart.calculateTotal()); // Update the checkout total
        });
        itemPanel.add(removeButton);

        cartItemsPanel.add(itemPanel);
    }

    cartFrame.add(scrollPane, BorderLayout.CENTER);
    cartFrame.add(checkoutButton, BorderLayout.SOUTH);

    cartFrame.setVisible(true);
}

    public static void main(String[] args) {
        new Customer();
    }
}
