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
