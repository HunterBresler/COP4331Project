package com.app;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer extends JFrame {
    private final List<Product> products = new ArrayList<>();
    private final ShoppingCart cart = new ShoppingCart();
    private final JPanel cartPanel = new JPanel();
    private final JPanel itemListPanel = new JPanel(); // Panel for item list

    public Customer() {
        initializeProducts();
        initializeUI();
    }

    private void initializeProducts() {
        for (int i = 0; i < 10; i++) {
            products.add(new Product("Product " + (i + 1), (i + 1) * 10));
        }
    }

    private void initializeUI() {
    setTitle("Customer Dashboard");
    setSize(1280, 720);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    setLayout(new BorderLayout(10, 10));
    add(createProductPanel(), BorderLayout.CENTER);

    cartPanel.setLayout(new BorderLayout());
    cartPanel.setBorder(BorderFactory.createTitledBorder("Shopping Cart"));
    cartPanel.setPreferredSize(new Dimension(300, cartPanel.getPreferredSize().height)); // Set preferred width

    itemListPanel.setLayout(new BoxLayout(itemListPanel, BoxLayout.Y_AXIS));
    JScrollPane scrollPane = new JScrollPane(itemListPanel); // Wrap item list in a scrollable pane
    cartPanel.add(scrollPane, BorderLayout.CENTER);
    add(cartPanel, BorderLayout.EAST);

    updateCartView();
    setVisible(true);
}


    private JPanel createProductPanel() {
        JPanel productPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        productPanel.setBorder(BorderFactory.createTitledBorder("Available Products"));

        for (Product product : products) {
            JButton productButton = new JButton(product.getName() + " - $" + product.getPrice());
            productButton.addActionListener(e -> {
                cart.addProduct(product);
                updateCartView();
            });
            productPanel.add(productButton);
        }

        return productPanel;
    }

        private void updateCartView() {
        itemListPanel.removeAll();
        cart.getProducts().forEach((product, quantity) -> itemListPanel.add(createCartItemPanel(product, quantity)));

        JButton checkoutButton = new JButton("Checkout - Total: $" + cart.calculateTotal());
        checkoutButton.addActionListener(e -> performCheckout());
        cartPanel.add(checkoutButton, BorderLayout.SOUTH); // Checkout button at the bottom
        itemListPanel.revalidate();
        itemListPanel.repaint();
    }


    private JPanel createCartItemPanel(Product product, int quantity) {
    JPanel itemPanel = new JPanel();
    itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));
    itemPanel.add(new JLabel(product.getName() + " - $" + product.getPrice() + " x "));

    JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel((int) quantity, 1, 100, 1));
    Dimension spinnerSize = new Dimension(60, 20); // Set preferred size dimensions
    quantitySpinner.setPreferredSize(spinnerSize);
    quantitySpinner.setMinimumSize(spinnerSize);
    quantitySpinner.setMaximumSize(spinnerSize); // Ensure the spinner adheres to these dimensions

    quantitySpinner.addChangeListener(e -> {
        int newQuantity = (int) quantitySpinner.getValue();
        cart.updateProductQuantity(product, newQuantity);
        updateCartView();
    });
    itemPanel.add(quantitySpinner);

    JButton removeButton = new JButton("Remove");
    removeButton.addActionListener(e -> {
        cart.removeProduct(product);
        updateCartView();
    });
    itemPanel.add(removeButton);

    return itemPanel;
}

    private void performCheckout() {
        JOptionPane.showMessageDialog(this, "Checkout completed. Total cost: $" + cart.calculateTotal());
        cart.clear();
        updateCartView();
    }

    public static void main(String[] args) {
        new Customer();
    }
}

class Product {
    private final String name;
    private final double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}

class ShoppingCart {
    private final Map<Product, Integer> productQuantities = new HashMap<>();

    public void addProduct(Product product) {
        productQuantities.put(product, productQuantities.getOrDefault(product, 0) + 1);
    }

    public void updateProductQuantity(Product product, int quantity) {
        if (quantity <= 0) {
            productQuantities.remove(product);
        } else {
            productQuantities.put(product, quantity);
        }
    }

    public void removeProduct(Product product) {
        productQuantities.remove(product);
    }

    public Map<Product, Integer> getProducts() {
        return new HashMap<>(productQuantities);
    }

    public double calculateTotal() {
        return productQuantities.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public void clear() {
        productQuantities.clear();
    }
}
