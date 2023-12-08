package com.login;

import javax.swing.*;
import java.awt.*;

/**
 * The LogInView class represents the view in the MVC pattern for the login functionality.
 * It is responsible for displaying the user interface for login, including fields for username, password, and user role.
 */
public class LogInView extends JFrame {
    private JComboBox<String> roleComboBox;
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JToggleButton showPasswordButton;
    private JLabel statusLabel;
    private JButton loginButton;

    /**
     * Constructs a new LogInView and initializes the user interface.
     */
    public LogInView() {
        initializeUI();
    }

    /**
     * Initializes the user interface components of the LogInView.
     */
    private void initializeUI() {
        setTitle("Shopping Cart Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        add(createMainPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Creates the main panel with input fields for username, password, and role.
     *
     * @return The created JPanel containing the input fields.
     */
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);

        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("Username:"), gbc);
        userTextField = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 0;
        mainPanel.add(userTextField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Password:"), gbc);
        passwordField = new JPasswordField(15);
        gbc.gridx = 1; gbc.gridy = 1;
        mainPanel.add(passwordField, gbc);
        showPasswordButton = new JToggleButton("👀");
        showPasswordButton.setPreferredSize(new Dimension(60, 20));
        gbc.gridx = 2; gbc.gridy = 1;
        mainPanel.add(showPasswordButton, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("Role:"), gbc);
        roleComboBox = new JComboBox<>(new String[]{"Customer", "Seller"});
        gbc.gridx = 1; gbc.gridy = 2;
        mainPanel.add(roleComboBox, gbc);

        return mainPanel;
    }

    /**
     * Creates the bottom panel with the login button and status label.
     *
     * @return The created JPanel containing the login button and status label.
     */
    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginButton = new JButton("Login");
        statusLabel = new JLabel();
        statusLabel.setForeground(Color.RED);
        bottomPanel.add(loginButton);
        bottomPanel.add(statusLabel);
        return bottomPanel;
    }

    // Getters for UI components
    public JComboBox<String> getRoleComboBox() {
        return roleComboBox;
    }

    public JTextField getUserTextField() {
        return userTextField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JToggleButton getShowPasswordButton() {
        return showPasswordButton;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}
