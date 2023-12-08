package com.app;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import com.inventory.Product;

/**
 * The CustomerController class serves as the controller in the MVC pattern for the Customer view.
 * It manages the interaction between the CustomerView and CustomerModel, handling user actions 
 * and updating the model and view accordingly.
 */
public class CustomerController {
    private CustomerModel model;
    private CustomerView view;

    /**
     * Constructs a CustomerController with the specified model and view.
     *
     * @param model The CustomerModel associated with this controller.
     * @param view The CustomerView associated with this controller.
     */
    public CustomerController(CustomerModel model, CustomerView view) {
        this.model = model;
        this.view = view;
        initController();
    }

    public CustomerController() {

    }

    /**
     * Initializes the controller by setting up action listeners for the view components.
     */
    public void initController() {
        JButton returnToStoreButton = view.getReturnToStoreButton();
        returnToStoreButton.addActionListener(e -> returnToStore());
    }

    /**
     * Sets the view associated with this controller.
     *
     * @param view The CustomerView to be associated with this controller.
     */
    public void setView(CustomerView view) {
        this.view = view;
    }

    /**
     * Retrieves a list of products.
     *
     * @return A List of Product objects.
     */
    public List<Product> getProducts() {
        return model.getProducts();
    }

    /**
     * Calculates the total cost of the items in the cart.
     *
     * @return The total cost as a double.
     */
    public double getCartTotal() {
        return model.getCart().calculateTotal();
    }

    /**
     * Retrieves the product catalog.
     *
     * @return A Map representing the product catalog, with product ID as key and Product object as value.
     */
    public Map<Integer, Product> getProductCatalog() {
        return model.getProductCatalog();
    }

    /**
     * Adds a product to the cart.
     * 
     * @param productId The ID of the product to be added to the cart.
     */
    public void addProductToCart(int productId) {
        Product product = model.getProductCatalog().get(productId);
        if (product != null) {
            model.getCart().addOrUpdateProduct(productId, 1);
            view.updateCartView();
        }
    }

    /**
     * Retrieves the model associated with this controller.
     *
     * @return The CustomerModel associated with this controller.
     */
    public CustomerModel getModel() {
        return model;
    }

    public void setModel(CustomerModel model) {
        this.model = model;
    }

    /**
     * Displays the contents of the shopping cart in a new frame.
     */
    public void displayCartContents() {
        // Implementation of displaying the cart contents...
    }

    /**
     * Handles the action to return to the main shopping area (store) from the cart.
     */
    public void returnToStore() {
        view.showStorePanel();
    }

    // Other controller methods...
}
