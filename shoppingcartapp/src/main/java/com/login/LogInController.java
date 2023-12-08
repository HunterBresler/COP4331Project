package com.login;

import com.app.CustomerModel;
import com.app.CustomerView;
import com.app.CustomerController;
import com.app.SellerController;
import com.app.SellerModel;
import com.app.SellerView;
import java.awt.event.ActionEvent;

/**
 * The LogInController class serves as the controller in the MVC pattern for the login functionality.
 * It manages the interaction between the LogInView and LogInModel, handling user actions and updating the model and view accordingly.
 */
public class LogInController {
    private LogInModel model;
    private LogInView view;

    /**
     * Constructs a LogInController with the specified model and view.
     *
     * @param model The LogInModel to be associated with this controller.
     * @param view The LogInView to be associated with this controller.
     */
    public LogInController(LogInModel model, LogInView view) {
        this.model = model;
        this.view = view;
        initController();
    }

    /**
     * Initializes the controller by setting up action listeners for the view components.
     */
    private void initController() {
        view.getLoginButton().addActionListener(this::processLogin);
        view.getShowPasswordButton().addActionListener(e -> togglePasswordVisibility());
    }

    /**
     * Processes the login attempt when the login button is clicked.
     * Opens the corresponding dashboard based on user role if login is successful.
     *
     * @param e The ActionEvent triggered by clicking the login button.
     */
    private void processLogin(ActionEvent e) {
        String role = (String) view.getRoleComboBox().getSelectedItem();
        String username = view.getUserTextField().getText();
        String password = new String(view.getPasswordField().getPassword());

        if (model.validateCredentials(username, password, role)) {
            view.getStatusLabel().setText("Login successful as " + role);
            if ("Seller".equals(role)) {
                openSellerDashboard();
            } else {
                openCustomerDashboard();
            }
        } else {
            view.getStatusLabel().setText("Invalid username or password");
        }
    }

    /**
     * Toggles the visibility of the password in the password field.
     */
    private void togglePasswordVisibility() {
        if (view.getShowPasswordButton().isSelected()) {
            view.getPasswordField().setEchoChar((char) 0);
            view.getShowPasswordButton().setText("👁️");
        } else {
            view.getPasswordField().setEchoChar('•');
            view.getShowPasswordButton().setText("👀");
        }
    }

    /**
 * Opens the Customer dashboard.
 */
private void openCustomerDashboard() {
    view.dispose();
    
    // Create instances of CustomerModel and CustomerView

    
    // Create a CustomerController and pass both the model and view
    CustomerController customerController = new CustomerController();

    CustomerModel customerModel = new CustomerModel();
    CustomerView customerView = new CustomerView(customerController);

    customerController.setModel(customerModel);
    
    // Set the view for the controller
    customerController.setView(customerView);
    customerController.initController();

    // Make the CustomerView visible
    customerView.setVisible(true);
}
    /**
     * Opens the Seller dashboard.
     */
    private void openSellerDashboard() {
        view.dispose();
        SellerModel sellerModel = new SellerModel("SellerName", "InventoryFileName.txt");
        SellerController sellerController = new SellerController(sellerModel);
        SellerView sellerView = new SellerView(sellerController);
        sellerController.setView(sellerView);
        sellerView.showFrame();
    }
}
