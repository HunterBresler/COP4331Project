package com.shoppingcart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerWindow extends JFrame {

    private Product[] products; // Initialize this with available products
    private ShoppingCart cart = new ShoppingCart(); // Initialize the shopping cart
    
    public CustomerWindow() {
        setTitle("Customer Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // For illustration, the products array needs to be initialized
        // products = ... // initialize with available products
        
        // Create a JList or JTable to display available products
        JList<Product> productList = new JList<>(products);
        add(new JScrollPane(productList), BorderLayout.CENTER);
        
        // Panel for cart total and checkout button
        JPanel cartPanel = new JPanel();
        JLabel cartTotalLabel = new JLabel("Cart Total: $" + cart.calculateTotalAmount());
        JButton checkoutButton = new JButton("Checkout");
        cartPanel.add(cartTotalLabel);
        cartPanel.add(checkoutButton);
        add(cartPanel, BorderLayout.SOUTH);
        
        // Add to Cart Button and its ActionListener
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product selectedProduct = productList.getSelectedValue();
                if (selectedProduct != null) {
                    // Add the selected product to the shopping cart
                    cart.addProduct(selectedProduct, 1); // Add 1 quantity of selected product
                    // Update the cart total label
                    cartTotalLabel.setText("Cart Total: $" + cart.calculateTotalAmount());
                }
            }
        });
        add(addToCartButton, BorderLayout.NORTH);
        
        pack();
    }
    
    // Other methods and classes (if necessary)
}