import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SellerController {
    private SellerModel model;
    private SellerView view;

    public SellerController() {
        this.model = new SellerModel("Selling User", "Inventory.txt");
        this.view = new SellerView(this);
        model.addObserver(view);
    }

    public void addToInventory(String productDetails) {
        // Logic to add a product to the Inventory.txt file
        model.addToInventory(productDetails);
    }

    public void deleteFromInventory(String productDetails) {
        model.deleteFromInventory(productDetails);
    }




}
