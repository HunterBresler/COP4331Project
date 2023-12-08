package com.app;

import javax.swing.*;
import java.awt.*;
import com.inventory.Product;

public class CustomerView extends JFrame {
    private final JPanel productsPanel;
    private final JLabel cartStatusLabel;
    private final CustomerController controller;
    private JButton returnToStoreButton;

    public CustomerView(CustomerController controller) {
        this.controller = controller;
        this.productsPanel = new JPanel();
        this.cartStatusLabel = new JLabel("Cart Total: $0.00");
        initializeUI();
    }

    public void updateCartView() {
        Cart cart = controller.getModel().getCart();
        double total = cart.calculateTotal();
        cartStatusLabel.setText("Cart Total: $" + String.format("%.2f", total));
    }

    private void initializeUI() {
        setTitle("Customer Dashboard");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        productsPanel.setLayout(new GridLayout(0, 2, 10, 10));
        for (Product product : controller.getProducts()) {
            JButton productButton = new JButton(product.getProductName() + " - $" + product.getSellingPrice());
            productButton.addActionListener(e -> controller.addProductToCart(product.getProductID()));
            productsPanel.add(productButton);
        }

        add(productsPanel, BorderLayout.CENTER);

        JButton cartButton = new JButton("View Cart");
        cartButton.addActionListener(e -> controller.displayCartContents());
        add(cartButton, BorderLayout.NORTH);

        add(cartStatusLabel, BorderLayout.SOUTH);

        returnToStoreButton = new JButton("Return to Store");
        returnToStoreButton.addActionListener(e -> controller.returnToStore());
        add(returnToStoreButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JButton getReturnToStoreButton() {
        return returnToStoreButton;
    }

    /**
     * Hides the current view, assuming it's the cart view, to simulate returning to the store.
     */
    public void showStorePanel() {
        this.setVisible(false); // Hide the current view
        // Additional logic to show the main store view would go here
        // For example, if you have a main store view, you would make it visible
        // mainStoreView.setVisible(true); // If you have a separate main store view
    }
}
