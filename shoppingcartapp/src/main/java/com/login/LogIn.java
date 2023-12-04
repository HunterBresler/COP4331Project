package com.login;

import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import java.awt.*;

public class LogIn extends JFrame {
    private Map<String, String> db = new HashMap<>();
    private JComboBox<String> roleComboBox;
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JToggleButton showPasswordButton;
    private JLabel statusLabel;

    public LogIn() {
        readDB();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Shopping Cart Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250); // Adjusted size to accommodate new components
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        add(createMainPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);

        // Role selection combo box
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(new JLabel("Role:"), gbc);
        roleComboBox = new JComboBox<>(new String[]{"Customer", "Seller"});
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(roleComboBox, gbc);

        // Username field
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        mainPanel.add(new JLabel("Username:"), gbc);
        userTextField = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 1;
        mainPanel.add(userTextField, gbc);

        // Password field and show password button
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        mainPanel.add(new JLabel("Password:"), gbc);
        passwordField = new JPasswordField(15);
        gbc.gridx = 1; gbc.gridy = 2;
        mainPanel.add(passwordField, gbc);
        showPasswordButton = new JToggleButton("👀");
        showPasswordButton.setPreferredSize(new Dimension(60, 20));
        showPasswordButton.addActionListener(e -> togglePasswordVisibility());
        gbc.gridx = 2; gbc.gridy = 2;
        mainPanel.add(showPasswordButton, gbc);

        return mainPanel;
    }

    private void togglePasswordVisibility() {
        if (showPasswordButton.isSelected()) {
            passwordField.setEchoChar((char) 0);
        } else {
            passwordField.setEchoChar('•');
        }
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> processLogin());
        statusLabel = new JLabel();
        statusLabel.setForeground(Color.RED);
        bottomPanel.add(loginButton);
        bottomPanel.add(statusLabel);
        return bottomPanel;
    }

    private void processLogin() {
        String role = (String) roleComboBox.getSelectedItem();
        String username = userTextField.getText();
        String password = new String(passwordField.getPassword());
        if (checkLogIn(username, password)) {
            statusLabel.setText("Login successful as " + role);
            // Here, you can add logic to handle different roles differently
        } else {
            statusLabel.setText("Invalid username or password");
        }
    }

    private void readDB() {
        db.put("user1", "pass1");
        db.put("user2", "pass2");
    }

    private boolean checkLogIn(String username, String password) {
        return password.equals(db.get(username));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LogIn());
    }
}
