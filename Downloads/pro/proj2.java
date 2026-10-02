import java.util.ArrayList;
import java.util.Scanner;

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

class CartItem {
    FoodItem food;
    int quantity;

    CartItem(FoodItem food, int quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    double getTotal() {
        return food.price * quantity;
    }
}

public class FoodOrderingSystem {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<FoodItem> menu = new ArrayList<>();
    static ArrayList<CartItem> cart = new ArrayList<>();

    public static void main(String[] args) {

        addMenuItems();

        System.out.println("======================================");
        System.out.println("       WELCOME TO FOOD EXPRESS");
        System.out.println("======================================");

        while (true) {

            System.out.println("\n1. View Menu");
            System.out.println("2. Add Food to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Remove Food from Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    viewMenu();
                    break;

                case 2:
                    addToCart();
                    break;

                case 3:
                    viewCart();
                    break;

                case 4:
                    removeFromCart();
                    break;

                case 5:
                    checkout();
                    break;

                case 6:
                    System.out.println("\nThank you for using Food Express!");
                    scanner.close();
                    return;

                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    static void addMenuItems() {

        menu.add(new FoodItem(1, "Pizza", 250));
        menu.add(new FoodItem(2, "Burger", 120));
        menu.add(new FoodItem(3, "French Fries", 100));
        menu.add(new FoodItem(4, "Biryani", 220));
        menu.add(new FoodItem(5, "Fried Rice", 150));
        menu.add(new FoodItem(6, "Noodles", 140));
        menu.add(new FoodItem(7, "Sandwich", 100));
        menu.add(new FoodItem(8, "Cold Drink", 60));
        menu.add(new FoodItem(9, "Ice Cream", 80));
    }

    static void viewMenu() {

        System.out.println("\n============= MENU =============");

        System.out.printf("%-5s %-20s %s%n", "ID", "Food", "Price");
        System.out.println("--------------------------------");

        for (FoodItem item : menu) {
            System.out.printf("%-5d %-20s Rs. %.2f%n",
                    item.id, item.name, item.price);
        }
    }

    static void addToCart() {

        viewMenu();

        System.out.print("\nEnter food ID: ");
        int id = scanner.nextInt();

        FoodItem selectedFood = null;

        for (FoodItem item : menu) {
            if (item.id == id) {
                selectedFood = item;
                break;
            }
        }

        if (selectedFood == null) {
            System.out.println("Food item not found.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        if (quantity <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }

        boolean found = false;

        for (CartItem item : cart) {

            if (item.food.id == id) {
                item.quantity += quantity;
                found = true;
                break;
            }
        }

        if (!found) {
            cart.add(new CartItem(selectedFood, quantity));
        }

        System.out.println(
                quantity + " x " + selectedFood.name + " added to cart."
        );
    }

    static void viewCart() {

        if (cart.isEmpty()) {
            System.out.println("\nYour cart is empty.");
            return;
        }

        System.out.println("\n============= YOUR CART =============");

        double subtotal = 0;

        for (CartItem item : cart) {

            double total = item.getTotal();
            subtotal += total;

            System.out.printf(
                    "%-20s x %-3d Rs. %.2f%n",
                    item.food.name,
                    item.quantity,
                    total
            );
        }

        System.out.println("-------------------------------------");
        System.out.printf("Subtotal: Rs. %.2f%n", subtotal);
    }

    static void removeFromCart() {

        if (cart.isEmpty()) {
            System.out.println("\nYour cart is empty.");
            return;
        }

        viewCart();

        System.out.print("\nEnter food ID to remove: ");
        int id = scanner.nextInt();

        boolean removed = false;

        for (int i = 0; i < cart.size(); i++) {

            if (cart.get(i).food.id == id) {
                cart.remove(i);
                removed = true;
                break;
            }
        }

        if (removed) {
            System.out.println("Food item removed from cart.");
        } else {
            System.out.println("Food item not found in cart.");
        }
    }

    static void checkout() {

        if (cart.isEmpty()) {
            System.out.println("\nYour cart is empty.");
            return;
        }

        System.out.println("\n============= BILL =============");

        double subtotal = 0;

        for (CartItem item : cart) {

            double total = item.getTotal();
            subtotal += total;

            System.out.printf(
                    "%-20s x %-3d Rs. %.2f%n",
                    item.food.name,
                    item.quantity,
                    total
            );
        }

        double deliveryCharge = 40;
        double tax = subtotal * 0.05;
        double grandTotal = subtotal + deliveryCharge + tax;

        System.out.println("--------------------------------");

        System.out.printf("Subtotal        : Rs. %.2f%n", subtotal);
        System.out.printf("Delivery Charge : Rs. %.2f%n", deliveryCharge);
        System.out.printf("Tax (5%%)        : Rs. %.2f%n", tax);
        System.out.printf("Grand Total     : Rs. %.2f%n", grandTotal);

        System.out.println("--------------------------------");

        System.out.print("Confirm order? (y/n): ");
        String confirmation = scanner.next();

        if (confirmation.equalsIgnoreCase("y")) {

            System.out.println("\nOrder placed successfully!");
            System.out.println("Your food will be delivered soon.");
            System.out.printf("Amount Paid: Rs. %.2f%n", grandTotal);

            cart.clear();

        } else {
            System.out.println("Order cancelled.");
        }
    }
}
