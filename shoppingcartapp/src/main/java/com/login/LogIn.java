package com.login;

import com.app.Customer;
import com.app.SellerController;
import com.app.SellerView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LogIn extends JFrame {
    // Database to store username and password pairs.
    private Map<String, UserCredentials> db = new HashMap<>();

    // UI components for the login screen.
    private JComboBox<String> roleComboBox; // Dropdown to select user role.
    private JTextField userTextField; // Field for entering username.
    private JPasswordField passwordField; // Field for entering password.
    private JToggleButton showPasswordButton; // Button to toggle password visibility.
    private JLabel statusLabel; // Label to display login status messages.

    // Constructor sets up the UI and initializes the database.
    public LogIn() {
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

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);

        // Username input field setup.
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("Username:"), gbc);
        userTextField = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 0;
        mainPanel.add(userTextField, gbc);

        // Password input field and show/hide password button.
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Password:"), gbc);
        passwordField = new JPasswordField(15);
        gbc.gridx = 1; gbc.gridy = 1;
        mainPanel.add(passwordField, gbc);
        showPasswordButton = new JToggleButton("👀");
        showPasswordButton.setPreferredSize(new Dimension(60, 20));
        showPasswordButton.addActionListener(e -> togglePasswordVisibility());
        gbc.gridx = 2; gbc.gridy = 1;
        mainPanel.add(showPasswordButton, gbc);

        // Role selection combo box setup.
        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("Role:"), gbc);
        roleComboBox = new JComboBox<>(new String[]{"Customer", "Seller"});
        gbc.gridx = 1; gbc.gridy = 2;
        mainPanel.add(roleComboBox, gbc);

        return mainPanel;
    }

    // Toggle the visibility of the password in the password field.
    private void togglePasswordVisibility() {
        if (showPasswordButton.isSelected()) {
            passwordField.setEchoChar((char) 0); // Password will be visible
            showPasswordButton.setText("👁️");
        } else {
            passwordField.setEchoChar('•'); // Password will be hidden
            showPasswordButton.setText("👀");
        }
    }

    // Creates the bottom panel with the login button.
    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this::processLogin);
        statusLabel = new JLabel();
        statusLabel.setForeground(Color.RED);
        bottomPanel.add(loginButton);
        bottomPanel.add(statusLabel);
        return bottomPanel;
    }

    // Processes login credentials when the login button is clicked.
    private void processLogin(ActionEvent e) {
        String role = (String) roleComboBox.getSelectedItem();
        String username = userTextField.getText();
        String password = new String(passwordField.getPassword());
        UserCredentials credentials = db.get(username + ":" + role);

        if (credentials != null && credentials.getPassword().equals(password)) {
            statusLabel.setText("Login successful as " + role);
            if ("Seller".equals(role)) {
                openSellerDashboard();
            } else {
                openCustomerDashboard();
            }
        } else {
            statusLabel.setText("Invalid username or password");
        }
    }

    // Opens the Customer dashboard.
    private void openCustomerDashboard() {
        this.dispose();
        new Customer().setVisible(true);
    }

    // Opens the Seller dashboard.
    private void openSellerDashboard() {
    this.dispose();
    SellerController sellerController = new SellerController();
    SellerView sellerView = new SellerView(sellerController);
    sellerView.showFrame();  // Show the internal frame of SellerView
}
    // Initializes the db from db.txt
    private void readDB() {
        try (BufferedReader reader = new BufferedReader(new FileReader("db.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 3) {
                    String username = parts[0];
                    String password = parts[1];
                    String role = parts[2];
                    db.put(username + ":" + role, new UserCredentials(username, password, role));
                } else {
                    System.out.println("Invalid line format in db.txt: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading db.txt");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LogIn());
    }
}

class UserCredentials {
    private String username;
    private String password;
    private String role;

    public UserCredentials(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
