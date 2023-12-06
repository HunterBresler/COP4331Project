package com.login;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.*;

import com.inventory.Inventory;
import com.inventory.Product;

public class LogIn extends JFrame {
    // Database to store username and password pairs.
    private Map<String, String> db = new HashMap<>();
    
    // UI components for the login screen.
    private JComboBox<String> roleComboBox; // Dropdown to select user role.
    private JTextField userTextField; // Field for entering username.
    private JPasswordField passwordField; // Field for entering password.
    private JToggleButton showPasswordButton; // Button to toggle password visibility.
    private JLabel statusLabel; // Label to display login status messages.

    // Constructor sets up the UI and initializes the database.
    public LogIn() {
        //db.put("user1", "pass1");
        //db.put("user2", "pass2");
        //writeCredentialsToFile();
        readDB(); // Load user credentials into the database.
        initializeUI(); // Initialize and setup the user interface.
    }

    // Initializes the user interface.
    private void initializeUI() {
        setTitle("Shopping Cart Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250); // Adjust size to fit all components.
        setLocationRelativeTo(null); // Center the window on screen.

        setLayout(new BorderLayout());
        add(createMainPanel(), BorderLayout.CENTER); // Add main panel with form fields.
        add(createBottomPanel(), BorderLayout.SOUTH); // Add bottom panel with login button.

        setVisible(true);
    }

    // Creates the main panel with form fields for user input.
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);

        // Role selection combo box setup.
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("Role:"), gbc);
        roleComboBox = new JComboBox<>(new String[]{"Customer", "Seller"});
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(roleComboBox, gbc);

        // Username input field setup.
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        mainPanel.add(new JLabel("Username:"), gbc);
        userTextField = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 1;
        mainPanel.add(userTextField, gbc);

        // Password input field and show/hide password button.
        gbc.gridx = 0; gbc.gridy = 2;
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

    // Toggle the visibility of the password in the password field.
    private void togglePasswordVisibility() {
        passwordField.setEchoChar(showPasswordButton.isSelected() ? (char) 0 : '•');
    }

    // Creates the bottom panel with the login button.
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

    // Processes login credentials when the login button is clicked.
    private void processLogin() {
        String role = (String) roleComboBox.getSelectedItem();
        String username = userTextField.getText();
        String password = new String(passwordField.getPassword());
        if (checkLogIn(username, password)) {
            statusLabel.setText("Login successful as " + role);
        } else {
            statusLabel.setText("Invalid username or password");
        }
    }

    // Initializes the db from db.txt
    private void readDB() 
    {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("db.txt"))) 
        {
            Object obj = inputStream.readObject();

            if (obj instanceof Map) 
            {
                this.db = (Map<String, String>) obj;
            } 
            else 
            {
                System.out.println("Invalid format in db.txt");
            }
        } 
        catch (IOException | ClassNotFoundException e) 
        {
            e.printStackTrace();
        }
    }

    // Method to write credentials to a text file
    public void writeCredentialsToFile() 
    {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("db.txt"))) 
        {
            outputStream.writeObject(db);
            System.out.println("Credentials written to db.txt");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }


    // Checks login credentials against the database.
    private boolean checkLogIn(String username, String password) {
        return password.equals(db.get(username));
    }

    // Main method to run the application.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LogIn());
    }
}
