import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SellerView implements SellerObserver {
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
