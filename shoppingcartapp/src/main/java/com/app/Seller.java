import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SellerView implements Observer {
    private JTextArea inventoryTextArea;
    private final JFrame frame;
    private final JLabel label;
    private final SellerController controller;

    public SellerView(SellerController controller) {
        this.controller = controller;
        this.label = new JLabel("Inventory Currently in Stock");
        this.frame = new JFrame();
        frame.setTitle("Seller View: " );
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(label);

        // Create components
        inventoryTextArea = new JTextArea();
        inventoryTextArea.setEditable(false);

        JButton updateInventoryButton = new JButton("Update Inventory");
//        updateInventoryButton.addActionListener(e -> showUpdateInventoryDialog());

        frame.setLayout(new BorderLayout());

        frame.add(new JScrollPane(inventoryTextArea), BorderLayout.CENTER);
        frame.add(updateInventoryButton, BorderLayout.SOUTH);

        // Load inventory initially
//        updateInventoryText();

        frame.setVisible(true);
    }

//    private void updateInventoryText() {
//        inventoryTextArea.setText("");
//        List<String> products = model.getProducts();
//        for (String product : products) {
//            inventoryTextArea.append(product + "\n");
//        }
//    }
//
//    private void showUpdateInventoryDialog() {
//        String newProduct = JOptionPane.showInputDialog(this, "Enter new product:");
//        if (newProduct != null && !newProduct.trim().isEmpty()) {
//            model.addToInventory(newProduct.trim());
//            updateInventoryText();
//        }
//    }

    @Override
    public void notify(String productDetails) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}


public class SellerModel {
    private String sellerName;
    private List<String> products;
    private List<Observer> observers = new ArrayList<>();
    private String inventoryFileName;

    public SellerModel(String sellerName, String inventoryFileName) {
        this.sellerName = sellerName;
        this.products = new ArrayList<>();
        this.inventoryFileName = inventoryFileName;

        // Load inventory from file on initialization
        loadInventory();
    }

    public String getSellerName() {
        return sellerName;
    }

    public List<String> getProducts() {
        return products;
    }

    private void loadInventory() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inventoryFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                products.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }

    private void saveInventory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inventoryFileName))) {
            for (String product : products) {
                writer.write(product);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }
    
    public void addToInventory(String productDetails) {
        products.add(productDetails);
        notifyObservers(productDetails);
        saveInventory();
    }

    public void deleteFromInventory(String productDetails) {
        products.removeIf(product -> product.contains(productDetails));
        notifyObservers(productDetails);
        saveInventory();
    }
    
    
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    
    private void notifyObservers(String productDetails) {
        for (Observer observer : observers) {
            observer.notify(productDetails);
        }
    }
}

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
