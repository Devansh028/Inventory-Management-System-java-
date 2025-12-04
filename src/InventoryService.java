import java.io.*;
import java.util.*;

public class InventoryService {

    private List<Product> products = new ArrayList<>();
    private static final String FILE_NAME = "products.txt";

    public InventoryService() {
        loadProducts();
    }

    private void loadProducts() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String category = data[2];
                double price = Double.parseDouble(data[3]);
                int qty = Integer.parseInt(data[4]);
                int limit = Integer.parseInt(data[5]);

                products.add(new Product(id, name, category, price, qty, limit));
            }
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            System.out.println("Error loading products: " + e.getMessage());
        }
    }

    private void saveProducts() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Product p : products) {
                bw.write(p.toString());
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error saving products: " + e.getMessage());
        }
    }

    public int generateProductId() {
        if (products.isEmpty())
            return 501;
        return products.get(products.size() - 1).getId() + 1;
    }

    public void addProduct(Product p) {
        products.add(p);
        saveProducts();
        System.out.println("Product added successfully!");
    }

    public void viewProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        products.forEach(System.out::println);
    }

    public void searchProduct(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                System.out.println(p);
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public void updateProduct(int id, String newName, String newCategory, double newPrice) {
        for (Product p : products) {
            if (p.getId() == id) {
                p.setName(newName);
                p.setCategory(newCategory);
                p.setPrice(newPrice);
                saveProducts();
                System.out.println("Product updated!");
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public void deleteProduct(int id) {
        boolean removed = products.removeIf(p -> p.getId() == id);
        if (removed) {
            saveProducts();
            System.out.println("Product deleted.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public void increaseStock(int id, int qty) {
        for (Product p : products) {
            if (p.getId() == id) {
                p.setQuantity(p.getQuantity() + qty);
                saveProducts();
                System.out.println("Stock increased!");
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public void decreaseStock(int id, int qty) {
        for (Product p : products) {
            if (p.getId() == id) {
                if (p.getQuantity() < qty) {
                    System.out.println("Not enough stock!");
                    return;
                }
                p.setQuantity(p.getQuantity() - qty);
                saveProducts();
                if (p.getQuantity() <= p.getLowStockLimit()) {
                    System.out.println("⚠ WARNING: Product stock is LOW!");
                }
                System.out.println("Stock decreased!");
                return;
            }
        }
        System.out.println("Product not found.");
    }
}
