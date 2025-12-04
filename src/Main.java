import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventoryService service = new InventoryService();
        while (true) {
            System.out.println("\n=== Inventory Management System ===");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Search Product");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Increase Stock");
            System.out.println("7. Decrease Stock");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    int id = service.generateProductId();

                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Category: ");
                    String category = sc.nextLine();

                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();

                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();

                    System.out.print("Enter Low Stock Limit: ");
                    int limit = sc.nextInt();

                    service.addProduct(new Product(id, name, category, price, qty, limit));
                    break;

                case 2:
                    service.viewProducts();
                    break;

                case 3:
                    System.out.print("Enter Product ID: ");
                    int sid = sc.nextInt();
                    service.searchProduct(sid);
                    break;

                case 4:
                    System.out.print("Enter Product ID: ");
                    int uid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    String nname = sc.nextLine();

                    System.out.print("Enter New Category: ");
                    String ncat = sc.nextLine();

                    System.out.print("Enter New Price: ");
                    double nprice = sc.nextDouble();

                    service.updateProduct(uid, nname, ncat, nprice);
                    break;

                case 5:
                    System.out.print("Enter Product ID: ");
                    int did = sc.nextInt();
                    service.deleteProduct(did);
                    break;

                case 6:
                    System.out.print("Enter Product ID: ");
                    int iid = sc.nextInt();
                    System.out.print("Increase Quantity By: ");
                    int inc = sc.nextInt();
                    service.increaseStock(iid, inc);
                    break;

                case 7:
                    System.out.print("Enter Product ID: ");
                    int did2 = sc.nextInt();
                    System.out.print("Decrease Quantity By: ");
                    int dec = sc.nextInt();
                    service.decreaseStock(did2, dec);
                    break;

                case 8:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
