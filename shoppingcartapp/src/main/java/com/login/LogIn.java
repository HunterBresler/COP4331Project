package com.login;

import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import java.awt.*;

public class LogIn extends JFrame {
    // Database map to store username and password pairs
    private Map<String, String> db = new HashMap<>();
    // Components of the login UI
    private JComboBox<String> roleComboBox; // Dropdown for selecting user role
    private JTextField userTextField; // Text field for inputting username
    private JPasswordField passwordField; // Password field for inputting password
    private JToggleButton showPasswordButton; // Button to toggle password visibility
    private JLabel statusLabel; // Label to show login status messages

    // Constructor
    public LogIn() {
        readDB(); // Initialize the database with user credentials
        initializeUI(); // Setup the user interface
    }

    // Initializes the user interface
    private void initializeUI() {
        setTitle("Shopping Cart Login"); // Title of the login window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Operation on close
        setSize(400, 250); // Size of the login window
        setLocationRelativeTo(null); // Center the window on screen

        setLayout(new BorderLayout()); // Layout manager for the frame
        add(createMainPanel(), BorderLayout.CENTER); // Add main panel to the frame
        add(createBottomPanel(), BorderLayout.SOUTH); // Add bottom panel to the frame

        setVisible(true); // Make the frame visible
    }

    // Creates the main panel with role selection, username, and password fields
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new GridBagLayout()); // Using GridBagLayout for flexible component placement
        GridBagConstraints gbc = new GridBagConstraints(); // Constraints for component placement
        gbc.insets = new Insets(4, 4, 4, 4); // Margins around components

        // Role selection combo box
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1;
        mainPanel.add(new JLabel("Role:"), gbc); // Role label
        roleComboBox = new JComboBox<>(new String[]{"Customer", "Seller"}); // Dropdown for role
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(roleComboBox, gbc); // Add role combo box to panel

        // Username field
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        mainPanel.add(new JLabel("Username:"), gbc); // Username label
        userTextField = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 1;
        mainPanel.add(userTextField, gbc); // Add username text field to panel

        // Password field and show password button
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        mainPanel.add(new JLabel("Password:"), gbc); // Password label
        passwordField = new JPasswordField(15);
        gbc.gridx = 1; gbc.gridy = 2;
        mainPanel.add(passwordField, gbc); // Add password field to panel
        showPasswordButton = new JToggleButton("👀"); // Button to toggle password visibility
        showPasswordButton.setPreferredSize(new Dimension(60, 20));
        showPasswordButton.addActionListener(e -> togglePasswordVisibility()); // Listener to toggle visibility
        gbc.gridx = 2; gbc.gridy = 2;
        mainPanel.add(showPasswordButton, gbc); // Add show password button to panel

        return mainPanel;
    }

    // Toggles the visibility of the password in the password field
    private void togglePasswordVisibility() {
        if (showPasswordButton.isSelected()) {
            passwordField.setEchoChar((char) 0); // Show password
        } else {
            passwordField.setEchoChar('•'); // Hide password
        }
    }

    // Creates the bottom panel with login button and status label
    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("Login"); // Login button
        loginButton.addActionListener(e -> processLogin()); // Action listener for login process
        statusLabel = new JLabel(); // Label to display status messages
        statusLabel.setForeground(Color.RED); // Color for error messages
        bottomPanel.add(loginButton); // Add login button to panel
        bottomPanel.add(statusLabel); // Add status label to panel
        return bottomPanel;
    }

    // Processes login credentials when login button is clicked
    private void processLogin() {
        String role = (String) roleComboBox.getSelectedItem(); // Get selected role
        String username = userTextField.getText(); // Get entered username
        String password = new String(passwordField.getPassword()); // Get entered password
        if (checkLogIn(username, password)) {
            statusLabel.setText("Login successful as " + role); // Display success message
            // Additional logic for different roles can be added here
        } else {
            statusLabel.setText("Invalid username or password"); // Display error message
        }
    }

    // Initializes the database with sample data (placeholder for actual database logic)
    private void readDB() {
        db.put("user1", "pass1"); // Sample user credential
        db.put("user2", "pass2"); // Another sample user credential
    }

    // Checks login credentials against the database
    private boolean checkLogIn(String username, String password) {
        return password.equals(db.get(username)); // Returns true if credentials match
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LogIn()); // Ensures GUI is created in the Event Dispatch Thread
    }
}
