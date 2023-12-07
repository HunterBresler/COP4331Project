package com.app;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SellerView implements SellerObserver {
    private JTextArea inventoryTextArea;
    private final JFrame frame;
    private final JLabel label;
    private SellerController controller;
    private JLabel profitLabel;

    public SellerView(SellerController controller) {
        this.controller = controller;
        this.label = new JLabel("Inventory Currently in Stock");
        this.frame = new JFrame();
        frame.setTitle("Seller View");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        inventoryTextArea = new JTextArea();
        inventoryTextArea.setEditable(false);
        
        profitLabel = new JLabel();
        frame.add(profitLabel, BorderLayout.SOUTH);

        // Buttons for inventory management
        JButton addProductButton = new JButton("Add Product");
        addProductButton.addActionListener(e -> showAddUpdateProductDialog(false));

        JButton updateProductButton = new JButton("Update Product");
        updateProductButton.addActionListener(e -> showAddUpdateProductDialog(true));

        // Layout for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addProductButton);
        buttonPanel.add(updateProductButton);

        frame.setLayout(new BorderLayout());
        frame.add(label, BorderLayout.NORTH);
        frame.add(new JScrollPane(inventoryTextArea), BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Load inventory initially
        updateInventoryText();
    }

    private void updateInventoryText() {
        if (controller == null) {
            System.out.println("SellerView: updateInventoryText called but controller is null");
            return;
        }
        inventoryTextArea.setText("");
        List<String> products = controller.getProducts();
        for (String product : products) {
            inventoryTextArea.append(product + "\n");
        }
    }
    
    public void updateProfitDisplay(double cost, double revenue, double profit) {
        profitLabel.setText("Cost: " + cost + ", Revenue: " + revenue + ", Profit: " + profit);
    }

    private void showAddUpdateProductDialog(boolean isUpdate) {
        String dialogTitle = isUpdate ? "Update Product" : "Add Product";
        String productDetails = JOptionPane.showInputDialog(frame, "Enter product details (id,name,quantity,invoicePrice,sellingPrice):", dialogTitle, JOptionPane.PLAIN_MESSAGE);

        if (productDetails != null && !productDetails.trim().isEmpty()) {
            controller.addOrUpdateProduct(productDetails.trim(), isUpdate);
            updateInventoryText();
        }
    }

    @Override
    public void notify(String productDetails) {
        updateInventoryText(); // Update the inventory list when notified
    }

    public void showFrame() {
        frame.setVisible(true);
    }

    public void setController(SellerController controller) {
        this.controller = controller;
        updateInventoryText(); // Refresh the inventory text when a new controller is set
    }
}
