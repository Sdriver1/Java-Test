
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class restaurant {

    public static void main(String[] args) {
        String[] foodMenu = {"Pizza", "Burger", "Pasta", "French Fries", "Sandwich"};
        double[] foodPrices = {5.00, 4.00, 6.00, 3.00, 2.00};

        String[] drinkMenu = {"Coke", "Pepsi", "Sprite", "Fanta", "Water"};
        double[] drinkPrices = {1.00, 1.00, 1.00, 1.00, 0.00};

        String[] dessertMenu = {"Ice Cream", "Cake", "Pie", "Cookies", "Brownies"};
        double[] dessertPrices = {3.00, 4.00, 5.00, 2.00, 3.00};

        List<String> orders = new ArrayList<>();
        List<Double> prices = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to Java Restaurant");

            while (true) {
                System.out.println("What menu would you like to see?");
                System.out.println("1. Food Menu | 2. Drink Menu | 3. Dessert Menu | 4. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {
                        System.out.println("Food Menu");
                        for (int i = 0; i < foodMenu.length; i++) {
                            System.out.println((i + 1) + ". " + foodMenu[i] + " - $" + foodPrices[i]);
                        }
                    }
                    case 2 -> {
                        System.out.println("Drink Menu");
                        for (int i = 0; i < drinkMenu.length; i++) {
                            System.out.println((i + 1) + ". " + drinkMenu[i] + " - $" + drinkPrices[i]);
                        }
                    }
                    case 3 -> {
                        System.out.println("Dessert Menu");
                        for (int i = 0; i < dessertMenu.length; i++) {
                            System.out.println((i + 1) + ". " + dessertMenu[i] + " - $" + dessertPrices[i]);
                        }
                    }
                    case 4 -> {
                        if (orders.isEmpty()) {
                            System.out.println("Thank you for visiting Java Restaurant");
                        } else {
                            printReceipt(orders, prices);
                            double total = calculateTotal(prices);
                            System.out.println("Your total amount to pay is: $" + total);
                            System.out.println("How would you like to pay? (Cash/Card)");
                            scanner.nextLine();
                            String payment = scanner.nextLine();
                            handlePayment(scanner, total, payment);
                        }
                        return;
                    }
                    default ->
                        System.out.println("Invalid choice");
                }

                scanner.nextLine();

                System.out.print("What would you like to order? ");
                String order = scanner.nextLine();

                if (isValidOrder(order, foodMenu, foodPrices, drinkMenu, drinkPrices, dessertMenu, dessertPrices, orders, prices)) {
                    System.out.println("You ordered " + order);
                } else {
                    System.out.println("Invalid choice");
                }

                System.out.print("Would you like to order anything else? (Yes/No) ");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("No")) {
                    printReceipt(orders, prices);
                    double total = calculateTotal(prices);
                    System.out.println("\nYour total amount to pay is: $" + total);
                    System.out.println("How would you like to pay? (Cash/Card)");
                    String payment = scanner.nextLine();
                    handlePayment(scanner, total, payment);
                    break;
                } else if (!answer.equalsIgnoreCase("Yes")) {
                    System.out.println("Invalid choice");
                    break;
                }
            }
        }
    }

    private static void printReceipt(List<String> orders, List<Double> prices) {
        System.out.println("\nReceipt:");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i) + " - $" + prices.get(i));
        }
        double total = calculateTotal(prices);
        System.out.println("Total: $" + total);
    }

    private static void handlePayment(Scanner scanner, double total, String payment) {
        while (true) {
            if (payment.equalsIgnoreCase("Cash")) {
                System.out.println("Please enter how much cash you are paying with:");
                double amount = scanner.nextDouble();
                if (amount < total) {
                    System.out.println("Insufficient amount. Please enter the correct amount to pay");
                } else if (amount > total) {
                    double change = amount - total;
                    System.out.println("Thank you for your payment, here is your change: $" + change);
                    break;
                } else {
                    System.out.println("Thank you for your payment, have a nice day!");
                    break;
                }
            } else if (payment.equalsIgnoreCase("Card")) {
                System.out.println("Processing your card payment...");
                System.out.println("Payment of $" + total + " successful. Thank you for your payment, have a nice day!");
                break;
            } else {
                System.out.println("Invalid payment choice. Please enter Cash or Card:");
                payment = scanner.nextLine();
            }
        }
    }

    private static boolean isValidOrder(String order, String[] foodMenu, double[] foodPrices,
            String[] drinkMenu, double[] drinkPrices,
            String[] dessertMenu, double[] dessertPrices,
            List<String> orders, List<Double> prices) {
        String[] parts = order.split(" ", 2);
        if (parts.length == 2) {
            try {
                int quantity = Integer.parseInt(parts[0]);
                String item = parts[1];

                for (int i = 0; i < foodMenu.length; i++) {
                    if (foodMenu[i].equalsIgnoreCase(item)) {
                        orders.add(quantity + " " + foodMenu[i]);
                        prices.add(foodPrices[i] * quantity);
                        return true;
                    }
                }
                for (int i = 0; i < drinkMenu.length; i++) {
                    if (drinkMenu[i].equalsIgnoreCase(item)) {
                        orders.add(quantity + " " + drinkMenu[i]);
                        prices.add(drinkPrices[i] * quantity);
                        return true;
                    }
                }
                for (int i = 0; i < dessertMenu.length; i++) {
                    if (dessertMenu[i].equalsIgnoreCase(item)) {
                        orders.add(quantity + " " + dessertMenu[i]);
                        prices.add(dessertPrices[i] * quantity);
                        return true;
                    }
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    private static double calculateTotal(List<Double> prices) {
        double total = 0;
        for (double price : prices) {
            total += price;
        }
        return total;
    }
}
