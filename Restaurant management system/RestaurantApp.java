import java.util.*;

class FoodItem {
    int id;
    String name;
    double price;

    FoodItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

class RestaurantApp {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<FoodItem> menu = new ArrayList<>();
    static ArrayList<FoodItem> cart = new ArrayList<>();

    static final String ADMIN_USER = "admin";
    static final String ADMIN_PASS = "1234";

    public static void main(String[] args) {

        // Default Menu
        menu.add(new FoodItem(1, "Idly", 30));
        menu.add(new FoodItem(2, "Dosa", 50));
        menu.add(new FoodItem(3, "Vada", 40));
        menu.add(new FoodItem(4, "Veg Biryani", 140));
        menu.add(new FoodItem(5, "Fried Rice", 120));

        int choice;

        do {
            System.out.println("\n===============================");
            System.out.println("   WELCOME TO JAVA RESTAURANT   ");
            System.out.println("===============================");
            System.out.println("1. Customer");
            System.out.println("2. Admin");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    customerMenu();
                    break;
                case 2:
                    adminLogin();
                    break;
                case 3:
                    System.out.println("Thank you! Visit again 😊");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 3);
    }

    // ---------------- CUSTOMER SECTION ----------------

    static void customerMenu() {
        int ch;
        do {
            System.out.println("\n------ CUSTOMER MENU ------");
            System.out.println("1. View Food Menu");
            System.out.println("2. Place Order");
            System.out.println("3. View Bill");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");
            ch = sc.nextInt();

            switch (ch) {
                case 1:
                    displayMenu();
                    break;
                case 2:
                    placeOrder();
                    break;
                case 3:
                    generateBill();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (ch != 4);
    }

    static void displayMenu() {
        System.out.println("\n------ FOOD MENU ------");
        System.out.printf("%-5s %-20s %-10s\n", "ID", "Item", "Price");
        for (FoodItem f : menu) {
            System.out.printf("%-5d %-20s Rs.%-10.2f\n", f.id, f.name, f.price);
        }
    }

    static void placeOrder() {
        char more;
        do {
            displayMenu();
            System.out.print("Enter Food ID: ");
            int id = sc.nextInt();

            FoodItem selected = null;
            for (FoodItem f : menu) {
                if (f.id == id) {
                    selected = f;
                    break;
                }
            }

            if (selected == null) {
                System.out.println("Invalid Item ID!");
                return;
            }

            cart.add(selected);
            System.out.println(selected.name + " added to cart.");

            System.out.print("Add more items? (y/n): ");
            more = sc.next().charAt(0);

        } while (more == 'y' || more == 'Y');
    }

    static void generateBill() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }

        double total = 0;

        System.out.println("\n---------- BILL ----------");
        for (FoodItem f : cart) {
            System.out.println(f.name + "  Rs." + f.price);
            total += f.price;
        }

        double gst = total * 0.05; // 5% GST
        double finalAmount = total + gst;

        System.out.println("---------------------------");
        System.out.println("Subtotal: Rs." + total);
        System.out.println("GST (5%): Rs." + gst);
        System.out.println("Total Payable: Rs." + finalAmount);
        System.out.println("---------------------------");

        cart.clear(); // clear after billing
    }

    // ---------------- ADMIN SECTION ----------------

    static void adminLogin() {
        sc.nextLine();
        System.out.print("Enter Username: ");
        String user = sc.nextLine();
        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        if (user.equals(ADMIN_USER) && pass.equals(ADMIN_PASS)) {
            adminMenu();
        } else {
            System.out.println("Invalid Login!");
        }
    }

    static void adminMenu() {
        int ch;
        do {
            System.out.println("\n------ ADMIN MENU ------");
            System.out.println("1. View Menu");
            System.out.println("2. Add Food Item");
            System.out.println("3. Remove Food Item");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            ch = sc.nextInt();

            switch (ch) {
                case 1:
                    displayMenu();
                    break;
                case 2:
                    addFoodItem();
                    break;
                case 3:
                    removeFoodItem();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (ch != 4);
    }

    static void addFoodItem() {
        System.out.print("Enter new Food ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Food Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        menu.add(new FoodItem(id, name, price));
        System.out.println("Food item added successfully!");
    }

    static void removeFoodItem() {
        displayMenu();
        System.out.print("Enter Food ID to remove: ");
        int id = sc.nextInt();

        Iterator<FoodItem> it = menu.iterator();
        boolean found = false;

        while (it.hasNext()) {
            FoodItem f = it.next();
            if (f.id == id) {
                it.remove();
                found = true;
                break;
            }
        }

        if (found)
            System.out.println("Food item removed!");
        else
            System.out.println("Item not found!");
    }
}
